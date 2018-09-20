# Apk Downloader
Download Android applications from Play Store automatically.

## Usage

#### Install the downloader .apk on the device
The simplest way is just to run `ApkDownloader.downloadApplications()`
from Android Studio. Alternatively, you can type in terminal:
```
$ adb push /path/to/apk/app-debug-androidTest.apk /data/local/tmp/com.blumental.apkdownloader.test
$ adb shell pm install -t -r "/data/local/tmp/com.blumental.apkdownloader.test"
```

#### Run the downloader with adb
To download necessary applications, run the apk with adb and provide
packages of the required applications as an argument:
```
$ adb shell am instrument -w -r \
-e debug false \
-e class 'com.blumental.apkdownloader.ApkDownloader#downloadApplications' \
-e apps com.avito.android,ru.yandex.taxi \
com.blumental.apkdownloader.test/android.support.test.runner.AndroidJUnitRunner
```