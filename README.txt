keywords API-26+ BroadcastReceiver example Custom Intent

Fri Oct 12 12:21:40 PDT 2018

As of API 26 registering a broadcast receiver no longer works in most cases.

See:https://developer.android.com/guide/components/broadcasts
    Changes to system broadcasts
        Android 8.0
Starting with API-26 you must use a context-registered receiver.
See the same URL as above:
    Context-registered receivers

The button invokes two BroadcastReceivers: one in a class file and the other an inner class to the main activity.

This code was tested on devices using
    google Nexus 7       Android 6.0.1  API=23 
    samsung SM-T820      Android 8.0.0  API=26 
    motorola XT830C      Android 4.4.4  API=19 

Testing with adb:
you can also run adb shell "am broadcast -a com.kana_tutor.CUSTOM_INTENT".  After running this you should see a toast message and on logcat you should see three messages saying:"Intent received:Intent...", "inner class intent received" an d OnCreate inner class intent..." which means both the inner class BroadcastReceivers and the class BroadcastReceiver received the intent.


Please let me know if you encounter problems.

Steve S.

