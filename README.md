##### Seep For Debug

很多时候我们接手一个项目的时候除了看遗留的文档看代码之外并没有什么好的方式，而且如果一个页面里面的组件是动态的，我们很难一次就能找到页面里面当前正在显示的组件到底是什么。
这个时候我们其实期望能有一种方式能知道现在显示在屏幕上某个位置的组件到底是什么，或者更进一步假设这是个RecyclerView，我们想知道每个holder对应的显示的bean到底是什么。
所以写了这么个工具，能让我们知道当前页面显示的是什么组件。

![seep_debug.gif](https://i.loli.net/2020/12/23/B1imevKaYjWLZoH.gif)

三指在屏幕上长按超过一秒会调出入口。使用了沪江的[gradle-android-plugin-aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)来做切面。

1、集成：rootProject的build.gradle中添加
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
    }
}
```
2、集成：主工程的build.gradle中添加
```groovy
apply plugin: 'android-aspectjx'

dependencies {
    implementation 'com.yocn:seep:1.2.3.beta'
}
```
3、使用：Manifest的application节点中添加以下节点：
```xml
<application/>
        <meta-data android:name="SEEP_UI_ENTRANCE" android:value="com.yocn.dumpanalysis.activity.BaseActivity"/>
        <meta-data android:name="SEEP_UI_BACK" android:value="com.yocn.dumpanalysis.activity.BaseActivity"/>
        <meta-data android:name="SEEP_NET_GET" android:value="com.yocn.dumpanalysis.activity.BaseActivity.get"/>
        <meta-data android:name="SEEP_NET_POST" android:value="com.yocn.dumpanalysis.activity.BaseActivity.post"/>
</application>
```
| 字段 | 含义 | 
| :---: | :---: | 
| SEEP_UI_ENTRANCE | 入口Activity | 
| SEEP_UI_BACK | 入口Activity | 
| SEEP_NET_GET | get方法入口 | 
| SEEP_NET_POST | post方法入口 | 

Seep的调用入口位置，3指长按屏幕超过1秒会出现操作入口，上面是用来制定入口位置，示例：
```java
package com.yocn.dumpanalysis.activity;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
```
可以使用一个BaseActivity来作为入口，那么所有继承了这个类的都可以实现三指长按调出操作入口。
