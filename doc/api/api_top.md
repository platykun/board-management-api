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

`/user/{id}`配下のリクエストはログイン認証が必要。  
ログインユーザと同一IDのリクエストのみ認可する。  

* [POST /user/login](user/login.md) - ログイン
* [PUT /user/{id}/create_room](user/user_id_create_room.md) - ルーム作成
* [PUT /user/{id}/join/{roomId}]() - ルーム参加
* [PUT /user/{id}/result]() - 結果登録
* [PUT /user/{id}/check_in]() - チェックイン
* [GET /user/{id}/status]() - ユーザステータス情報


### Room

* [GET /room/find_all/{page}](room/room_find_all_page.md) - ルーム一覧検索

### BoardGame

* [GET /boardGame/list/{name}]() - ボードゲーム一覧

### Place

* [GET /place/list/{name}]() - 場所一覧

## 注意点

実装中のため仕様が変更される場合があります。

## API利用規約


## お問い合わせ

