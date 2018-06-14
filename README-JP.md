[English](https://github.com/aromajoin/controller-sdk-java) / [日本語](README-JP.md)

# ControllerSDK - Java版 &middot; [ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Ajvm/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm/_latestVersion) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

**[Aroma Shooter](https://aromajoin.com/hardware/shooters/aroma-shooter-1)との通信に使用されるAromaShooterController SDKのJava版です。**  

# 目次
1. [対応デバイス](#対応デバイス)  
2. [前提条件](#前提条件)
3. [インストール](#インストール)
4. [使用法](#使用法)
    * [セットアップ](#セットアップ)
    * [デバイスのスキャンと接続](#デバイスのスキャンと接続)
    * [接続されたデバイス](#接続されたデバイス)
    * [香りを拡散する](#香りを拡散する)
    * [拡散を止める](#拡散を止める)
    * [切断する](#切断する)
5. [問題](#問題)
6. [ライセンス](#ライセンス)

## 対応デバイス
* Aroma Shooter 1 USB
* Aroma Shooter 1 RS-485

## 前提条件
* JRE版: >= 1.8+
* [ドライバ](http://www.ftdichip.com/FTDrivers.htm)をダウンロードしてインストールしてください。

## インストール
### Gradle

1. まず、[rootProject]/build.gradleの上にリポジトリを追加します。
```gradle
repositories {
    // ... other repositories
    maven { url "https://dl.bintray.com/aromajoin/maven/" }
}
```
2. 次に、`controller-sdk`依存関係を追加します。
```gradle
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:jvm:2.x.x'
}
```
### Maven
```xml
<dependency>
  <groupId>com.aromajoin.sdk</groupId>
  <artifactId>jvm</artifactId>
  <version>2.x.x</version>
  <type>pom</type>
</dependency>
```
### バイナリファイル（.jar)
1. 最新の* .jarファイルを[Bintray](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm#files/com/aromajoin/sdk/jvm)から直接ダウンロードしてください。
2. それをプロジェクトのビルドパスに追加します。

## 使用法
詳細は[サンプルプロジェクト](https://github.com/aromajoin/controller-sdk-java/tree/master/Sample)をチェックしてください。

### セットアップ
```java
// Initialize an USB Aroma Shooter
USBASController usbController = new USBASController();
```
### デバイスのスキャンと接続

```java
usbController.scanAndConnect(new DiscoverCallback() {
    @Override
    public void onDiscovered(List<AromaShooter> aromaShooters) {
        for(AromaShooter aromaShooter : aromaShooters){
            // Detected Aroma Shooter.
        }
    }

    @Override
    public void onFailed(String msg) {
       // Failed on scanning.
    }
});
```

### 接続されたデバイス
```java
List<AromaShooter> connectedDevices = usbController.getConnectedDevices();
```

### 香りを拡散する 
```java
/**
 * @param duration     拡散持続時間（ミリ秒）。
 * @param booster      ブースターが使用されているかどうかを判定する。(true: より強く拡散する, false: より弱く拡散する)
 * @param ports        カートリッジ番号を拡散する。値：1 ~ 6.
 */
// 例：以下のコードは、カートリッジ2と5を3秒間拡散します。
usbController.diffuseAll(3000, true, 2, 5);
```
### 拡散を止める
拡散している場合は、現在接続されているデバイスのすべてのポートを停止する。
```java
usbController.stopAllPorts();
```
### 切断する
すべてのデバイスを切断する。
```java
usbController.disconnectAll();
```
## 問題
**問題が発生したり、新機能が必要な場合は、[新しい問題](https://github.com/aromajoin/controller-sdk-java/issues)を作成してください。**

## ライセンス
[こちら](https://github.com/aromajoin/controller-sdk-windows/blob/master/LICENSE.md)を参照してください。
