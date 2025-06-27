[![Release](https://github.com/Suzu008/OpenListFlutter/actions/workflows/release.yaml/badge.svg)](https://github.com/Suzu008/OpenListFlutter/actions/workflows/release.yaml)
[![Test](https://github.com/Suzu008/OpenListFlutter/actions/workflows/build.yaml/badge.svg)](https://github.com/Suzu008/OpenListFlutter/actions/workflows/build.yaml)
[![CheckAList](https://github.com/Suzu008/OpenListFlutter/actions/workflows/sync_openlist.yaml/badge.svg)](https://github.com/Suzu008/OpenListFlutter/actions/workflows/sync_openlist.yaml)

# OpenListFlutter

OpenListFlutter是一个基于OpenList的Android服务端，Fork自[AlistFlutter](https://github.com/jing332/AListFlutter)，使用Google Flutter作为UI框架。

> [Github Actions](https://github.com/Suzu008/OpenListFlutter/actions/workflows/sync_openlist.yaml)
> 每日早晚五点钟检查最新的 [OpenList](https://github.com/OpenListTeam/OpenList/releases)
> 并自动构建APK，发布到 [Release](https://github.com/Suzu008/OpenListFlutter/releases)
> 中，您只需耐心等待片刻并在应用内检查更新即可。

<img src="./images/alist.jpg" height="150px">

### Bug
- Android4.4闪退 https://github.com/jing332/AListFlutter/issues/5
- 部分设备无法添加本地存储 https://github.com/jing332/AListFlutter/issues/2

### 关于IOS
理论上 [Gomobile](https://pkg.go.dev/golang.org/x/mobile/cmd/gomobile?utm_source=godoc#hdr-Build_a_library_for_Android_and_iOS) 支持IOS，但由于本人无IOS相关开发设备，故无法支持。

# Download

- [Github Action (DEV)](https://github.com/Suzu008/OpenListFlutter/actions/workflows/build.yaml) 开发版

