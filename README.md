# Lean Analytics SDK

A lean and light SDK for easily tracking application events by supplying 
you the hooks so can connect them to your favourite analytics SDK. 
Currently supported events:
* Start of a new screen (activity)
* Click on a clickable view

Basically, the first point is what Firebase does out of the box, however, 
if you cannot or do want to use Firebase analytics then this SDK might 
work for you.

Note: intercepting view clicks uses reflection and therefor has no
guarentees whatsoever to continue working in the future. 

## Setup

Step 1: Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2: Add the dependency to your app's build file

	dependencies {
	        implementation 'com.github.roderiklagerweij:LeanAnalytics:0.1'
	}
	

## Usage

In your Application class (if you don't have one yet add one) modify the 
onCreate to initialise the SDK:

    override fun onCreate() {
        super.onCreate()

        LeanAnalyticsSdk.init(this, object : TrackPageAdapter {
            override fun trackAction(id: String) {
                // track here the action to your actual analytics SDK
            }

            override fun trackActivity(activityName: String) {
                // track here the page to your actual analytics SDK
            }
        })
    }

In the trackAction and trackActivity functions you can add the code that 
delegates the events to your analytics SDK, e.g.:

    override fun trackActivity(activityName: String) {
        Analytics.trackState(activityName, null);
    }

## Configuration

* Track page when activity is created (default) or resumed

You can configure the SDK to not track the activity when it is created but
rather when it is resumed:

    LeanAnalyticsSdk.setTrackPageConfiguration(TrackPageConfiguration.TRACK_ONRESUME)
    
Note: setting to track when the activity is resumed triggers a callback when
navigating *back* to an activity as well as navigating forward to an activity.

## Limitations

* Obfuscation apps are not supported: if activities are obfuscated it will 
pass the obfuscated name, which obviously is useless
* Click listeners set after some async operation will not but intercepted:
since the click listeners are replaced with a click listener that notifies 
the SDK of the click event on the onResume lifecycle callback of the activity,
any click listeners set after that will not notify you of the event