# Android MVI 
[![](https://jitpack.io/v/wosika/MVI-Kale.svg)](https://jitpack.io/#wosika/MVI-Kale)  [![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu) <br/>

## Work flow
![](https://github.com/wosika/MVI-Kale/blob/master/designs/work-flow.png)
### How to use
  1.Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  2.Add the dependency
  
    dependencies {
	           implementation 'com.github.wosika:MVI-Kale:0.0.2'
    }
### 0.0.2 Update 
1.Replace LiveData to StateFlow.

2.Upgrade gradle version.

3.remove InstallProvider.

4.set processIntent function to suspend.
