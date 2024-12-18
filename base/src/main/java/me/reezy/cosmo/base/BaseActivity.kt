package me.reezy.cosmo.base

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId), BaseView {


    fun requireView(): View {
        return findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        fixClassLoader(savedInstanceState)
        super.onCreate(savedInstanceState)
        onSetupUI()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        fixClassLoader(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    override fun onResume() {
        foregroundRef = WeakReference(this)
        super.onResume()

    }


    private fun fixClassLoader(bundle: Bundle?) {
        if (bundle != null && (Build.VERSION.SDK_INT == 28 || Build.VERSION.SDK_INT == 29)) {
            bundle.classLoader = classLoader

            for (key in bundle.keySet()) {
                @Suppress("DEPRECATION")
                val value = bundle.get(key)
                if (value is Bundle) {
                    value.classLoader = classLoader
                }
            }
        }
    }



    companion object {
        private var foregroundRef: WeakReference<BaseActivity?> = WeakReference(null)

        val foreground: BaseActivity?
            get() = foregroundRef.get()?.let {
                if (it.isFinishing || it.isDestroyed) null else it
            }
    }

}