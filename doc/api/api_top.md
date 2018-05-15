# APIリファレンス

## 概要
TODO: 概要を記載.

## 仕様

### 認証

認証方式について記載


### レスポンス

JSON形式でレスポンスを返します。

正常な場合はHTTPステータスコード 200 OKを返します。

エラーの場合はHTTPステータスコード 400 Bad Requestを返します。

エラーレスポンスの例(未定)

```
{
  "error":"invalid_request",
  "error_description":"アクセストークンが無効です。"
}
```

## API

### Login

ログイン用のトークンを発行

### Users

* [POST /user/login](user/login.md) - ルーム作成
* [PUT /user/{id}/create_room](user/user_id_create_room.md) - ルーム作成

### Room

* [GET /room/find_all/{page}](room/room_find_all_page.md) - ルーム一覧検索

## 注意点

実装中のため仕様が変更される場合があります。

## API利用規約


## お問い合わせ

