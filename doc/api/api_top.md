# APIリファレンス

## 概要
TODO: 概要を記載.

## 仕様

### 認証

認証方式について記載


### レスポンス

JSON形式でレスポンスを返します。

正常な場合はHTTPステータスコード 200 OKを返します。

エラーの場合はHTTPステータスコード 400 Bad Request等を返します。

エラーレスポンスの例  

```
{
  "type": "failure",
  "errors": [
    {
      "message": "Sorry, that page does not exist",
      "code": 34
    }
  ]
}
```

### レスポンスコード設計

レスポンスコードは下記を基準とする。
[参考サイト](https://qiita.com/mserizawa/items/b833e407d89abd21ee72)
```
200 OK - GET, PUT, PATCH, DELETE リクエストが成功した場合に応答。もしくは、POST リクエストが結果的に何もリソースを作らなかった場合に応答。
201 Created - POST リクエストがリソース作成に成功した場合に応答。なお、そのリソースへのリンクを Location ヘッダに含める必要がある。
204 No Content - 成功したDELETE リクエストで、ボディを返したくない場合に応答
304 Not Modified - HTTP キャッシュが有効な場合に応答
400 Bad Request - パース不可能なリクエストボディが来た場合に応答
401 Unauthorized - 認証がされていない、もしくは不正なトークンの場合に応答
403 Forbidden - 認証はされているが、認可されていないリソースへのリクエストに応答
404 Not Found - 存在しないリソースへのリクエストに応答
405 Method Not Allowed - 認可されていないメソッドでのリクエストに応答
410 Gone - 今は存在しないリソース（廃止されたAPIなど）で空要素を返す場合などに応答
415 Unsupported Media Type - 対応していない MediaType が指定された場合に応答
422 Unprocessable Entity - バリデーションエラーに対して応答
429 Too Many Requests - 回数制限をオーバーしたリクエストに対して応答
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

