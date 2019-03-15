# Lean Analytics SDK



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
            override fun trackActivity(activityName: String) {
                // track here the page to your actual analytics SDK
            }
        })
    }

In the trackActivity function you can add the code that delegates the tracking
to your analytics SDK, e.g.:

    override fun trackActivity(activityName: String) {
        Analytics.trackState(activityName, null);
    }

