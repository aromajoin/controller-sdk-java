[English](https://github.com/aromajoin/controller-sdk-java) / [日本語](README-JP.md)

# ControllerSDK - Java版 &middot; [ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Ajvm/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm/_latestVersion) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

**[Aroma Shooter](https://aromajoin.com/products/aroma-shooter)との通信に使用されるAromaShooterController SDKのJava版です。**  

# 目次
1. [対応デバイス](#対応デバイス)  
2. [前提条件](#前提条件)
3. [インストール](#インストール)
4. [使用法](#使用法)
    * [セットアップ](#セットアップ)
    * [デバイスのスキャンと接続](#デバイスのスキャンと接続)
    * [接続されたデバイス](#接続されたデバイス)
    * [香りを噴射する](#香りを噴射する)
    * [噴射を止める](#噴射を止める)
    * [切断する](#切断する)
5. [問題](#問題)
6. [ライセンス](#ライセンス)

## 対応デバイス
* Aroma Shooter USBタイプ
* Aroma Shooter 2

## 対応接続
* USB 

## 前提条件
* JRE版: >= 1.8+
* [ドライバ](http://www.ftdichip.com/FTDrivers.htm)をダウンロードしてインストールしてください。

## インストール
### Gradle

1. 初めに[rootProject]/build.gradleの上にリポジトリを追加します。
```gradle
repositories {
    // ... other repositories
    maven { url "https://dl.bintray.com/aromajoin/maven/" }
}
```
2. 次に`controller-sdk`依存関係を追加します。
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
// USBAromaShooterを初期化する
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

### 香りを噴射する
```java
/**
 * @param duration     噴射持続時間（ミリ秒）。
 * @param booster      ブースターが使用されているかどうかを判定する。(true: より強く噴射する, false: より弱く噴射する)
 * @param ports        カートリッジ番号を噴射する。値：1 ~ 6.
 */
// 例：以下のコードは、カートリッジ2と5を3秒間噴射します。
usbController.diffuseAll(duration, boosterIntensity, fanIntensity, ports);
```

* AS2（Aroma Shooter 2）デバイスのみのディフューザー香りメソッド
```java
/**
 * AS2のすべての接続デバイスからの特定のポートでの香りの拡散
 * @param duration              拡散時間（ミリ秒）
 * @param boosterIntensity      ブースターポート。値： 0~100
 * @param fanIntensity          ファンポート。値： 0~100
 * @param ports                 ポートの配列。値: Port(portNumber, portIntensity)
 */
 
usbController.diffuseAll(duration, boosterIntensity, fanIntensity, ports);
```

* その他の噴射法
```java
// 1つのアロマシューターに香りを噴射する
controller.diffuse(aromaShooter, duration, booster, ports);
// シリアル番号入力で1つのアロマシューターに香りを噴射する
controller.diffuse(aromaShooterSerial, duration, booster, ports);
// 1つのアロマシューターに香りを噴射する [アロマシューター2のみがサポートされています](香りの強度を調整可)
controller.diffuse(aromaShooter, durationMilliSec, boosterIntensity, fanIntensity, ports);
// シリアル番号入力で1つのアロマシューターに香りを噴射する [アロマシューター2のみがサポートされています](香りの強度を調整可)
controller.diffuse(aromaShooterSerial, durationMilliSec, boosterIntensity, fanIntensity, ports);
```

### 噴射を止める
噴射している場合は、接続されているデバイスのすべてのポートを停止します。
```java
usbController.stopAllPorts();
```
### 切断する
すべてのデバイスを切断します。
```java
usbController.disconnectAll();
```
## 問題
**問題が発生したり、新機能が必要な場合は、[新しい問題](https://github.com/aromajoin/controller-sdk-java/issues)を作成してください。**

## ライセンス
[こちら](https://github.com/aromajoin/controller-sdk-java/blob/master/LICENSE.md)を参照してください。
