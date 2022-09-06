# base

页面基类 BaseActivity/BaseFragment 统一了子类初始化UI(onSetupUI)与首次加载数据(onLoadData)的位置

## Gradle

``` groovy
repositories {
    maven { url "https://gitee.com/ezy/repo/raw/cosmo/"}
}
dependencies {
    implementation "me.reezy.cosmo:base:0.7.0"
}
```
## 使用

```kotlin
class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onSetupUI() {
         // TODO
    }

    override fun onLoadData(isRefresh: Boolean) {
         // TODO
    }
}

class SimpleFragment: BaseFragment(R.layout.layout_simple) {

    override fun onSetupUI() {
         // TODO
    }

    override fun onLoadData(isRefresh: Boolean) {
         // TODO
    }
}
```

## LICENSE

The Component is open-sourced software licensed under the [Apache license](LICENSE).