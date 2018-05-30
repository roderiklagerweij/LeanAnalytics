package com.rl.analyticstest

import android.util.Log
import android.view.View
import android.view.ViewGroup



/**
 * Created by Roderik on 23-05-18.
 */
class IdClickInjector {

    companion object {
        fun inject(rootView : ViewGroup) {
            recurseView(rootView)
        }

        private fun recurseView(view : View) {
            val tic = System.currentTimeMillis()
            val viewgroup = view as ViewGroup
            for (i in 0 until viewgroup.childCount) {
                val childView = viewgroup.getChildAt(i)

                if (childView.hasOnClickListeners()) {
                    val clickListener = getOnClickListenerV14(childView)
                    if (clickListener !is IdClickListener) {
                        childView.setOnClickListener(clickListener?.let { IdClickListener(it) })
                    }
                }

                if (childView is ViewGroup) recurseView(childView)
            }
            Log.d("Injector", "Injecting done in " + (System.currentTimeMillis()-tic) + " ms")
        }

        private fun getOnClickListenerV14(view: View): View.OnClickListener? {
            var retrievedListener: View.OnClickListener? = null
            val viewStr = "android.view.View"
            val lInfoStr = "android.view.View\$ListenerInfo"

            try {
                val listenerField = Class.forName(viewStr).getDeclaredField("mListenerInfo")
                var listenerInfo: Any? = null

                if (listenerField != null) {
                    listenerField.isAccessible = true
                    listenerInfo = listenerField.get(view)
                }

                val clickListenerField = Class.forName(lInfoStr).getDeclaredField("mOnClickListener")

                if (clickListenerField != null && listenerInfo != null) {
                    retrievedListener = clickListenerField.get(listenerInfo) as View.OnClickListener
                }
            } catch (ex: NoSuchFieldException) {
                Log.e("Reflection", "No Such Field.")
            } catch (ex: IllegalAccessException) {
                Log.e("Reflection", "Illegal Access.")
            } catch (ex: ClassNotFoundException) {
                Log.e("Reflection", "Class Not Found.")
            }

            return retrievedListener
        }

    }

}