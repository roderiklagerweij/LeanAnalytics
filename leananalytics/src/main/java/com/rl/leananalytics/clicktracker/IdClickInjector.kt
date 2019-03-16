package com.rl.leananalytics.clicktracker

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.rl.leananalytics.TrackingAdapter

class IdClickInjector {

    companion object {
        fun inject(rootView : ViewGroup, trackingAdapter: TrackingAdapter) {
            val tic = System.currentTimeMillis()

            recurseView(rootView, rootView.resources, trackingAdapter)
            Log.d("LeanAnalyticsSdk", "Injecting done in " + (System.currentTimeMillis()-tic) + " ms")
        }

        private fun recurseView(view : View, resources : Resources, trackingAdapter: TrackingAdapter) {

            val viewgroup = view as ViewGroup
            for (i in 0 until viewgroup.childCount) {
                val childView = viewgroup.getChildAt(i)
                if (childView.hasOnClickListeners()) {
                    val clickListener = getOnClickListenerV14(childView)
                    if (clickListener !is IdClickListener) {
                        if (childView.id != -1) {
                            childView.setOnClickListener(clickListener?.let { IdClickListener(it, trackingAdapter) })
                        }
                    }
                }

                if (childView is ViewGroup) recurseView(childView, resources, trackingAdapter)
            }
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
            } catch (ex: IllegalAccessException) {
            } catch (ex: ClassNotFoundException) {
            }

            return retrievedListener
        }
    }
}