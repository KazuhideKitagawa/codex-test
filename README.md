# Spring Boot + MyBatis Sample API

このリポジトリは、Java 21・Spring Boot 3 と MyBatis を使用して、MySQL と連携するシンプルな REST API のサンプルプロジェクトです。ビルドツールは Gradle (Groovy DSL) を利用しています。

## 主な技術スタック

- Java 21
- Spring Boot 3 (spring-boot-starter-web / validation)
- MyBatis Spring Boot Starter
- MySQL 8 以降（開発用サンプルでは H2 を利用したテスト構成を同梱）
- Gradle 8 (Groovy DSL)

## プロジェクト構成

```
app/
  ├─ src/main/java/com/example/demo/
  │    ├─ SpringMybatisSampleApplication.java
  │    └─ user/
  │         ├─ User.java
  │         ├─ UserController.java
  │         ├─ UserMapper.java
  │         └─ UserService.java
  ├─ src/main/resources/
  │    ├─ application.yml
  │    ├─ data.sql
  │    ├─ schema.sql
  │    └─ mapper/UserMapper.xml
  └─ src/test/java/com/example/demo/
       └─ SpringMybatisSampleApplicationTests.java
```

## MySQL の設定

`src/main/resources/application.yml` に含まれる接続情報を、利用する MySQL 環境に合わせて書き換えてください。

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sample_db?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8
    username: sample_user
    password: sample_password
```

テーブル定義および初期データは `schema.sql` と `data.sql` に記述されています。

```sql
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
```

## セットアップ

リポジトリにはポリシーにより Gradle Wrapper のバイナリ (`gradle/wrapper/gradle-wrapper.jar`) を含めていません。初回セットアップ時はローカルにインストールされた Gradle で Wrapper を生成してください。

```bash
gradle wrapper
```

コマンド実行後に `./gradlew` で通常どおりビルドや起動が行えます。

## 起動方法

```bash
./gradlew bootRun
```

アプリケーションが起動すると `http://localhost:8080/api/users` にユーザー API が公開されます。

## サンプル API

| メソッド | パス | 説明 |
|----------|------|------|
| GET | `/api/users` | 登録されているユーザー一覧を返却 |
| GET | `/api/users/{id}` | 指定 ID のユーザー情報を返却 |
| POST | `/api/users` | ユーザーを新規登録（name, email を JSON で指定） |
| PUT | `/api/users/{id}` | ユーザー情報を更新 |
| DELETE | `/api/users/{id}` | ユーザーを削除 |

POST/PUT のリクエスト例:

```http
POST /api/users HTTP/1.1
Content-Type: application/json

{
  "name": "Jiro Tanaka",
  "email": "jiro@example.com"
}
```

## テスト

テストでは H2 データベース (MySQL 互換モード) を使用し、Spring Boot の起動と MyBatis のマッピングが正しく動作するかを検証しています。

```bash
./gradlew test
```
