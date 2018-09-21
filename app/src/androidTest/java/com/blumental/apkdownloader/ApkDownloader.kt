package com.blumental.apkdownloader

import android.content.Intent
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class ApkDownloader {

  private lateinit var apps: List<String>

  private lateinit var device: UiDevice

  @Before
  fun setUp() {
    device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    apps = InstrumentationRegistry.getArguments()?.get("apps")
        ?.toString()?.split(",") ?: emptyList()
  }

  @Test
  fun downloadApplications() = apps.forEach {
    download(it)
    TimeUnit.SECONDS.sleep(2)
  }

  private fun download(packageName: String) {
    Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        .let { InstrumentationRegistry.getContext().startActivity(it) }

    /* click INSTALL */
    device
        .wait(Until.findObject(By.text("INSTALL")), SHORT_TIMEOUT)
        ?.click() ?: return

    /* grant permissions if necessary */
    device
        .wait(Until.findObject(By.text("ACCEPT")), SHORT_TIMEOUT)
        ?.click()

    /* wait until UNINSTALL appears */
    device
        .wait(Until.hasObject(By.text("UNINSTALL")), LONG_TIMEOUT)

    device.pressHome()
  }
}

private const val SHORT_TIMEOUT = 3 * 1000L
private const val LONG_TIMEOUT = 5 * 60 * 1000L
