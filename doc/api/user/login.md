# POST user/login

ログイン処理

## リクエストパラメーター

| Name         | Description                      |
|--------------|----------------------------------|
| userId   | ユーザID(必須)         |
| password    | パスワード(必須)                      |

## リクエストの例

`POST http://localhost:8080/user/login`  

```
{
  "userId":"user001",
  "password":"password"
}
```

## レスポンスの例

```
{
  "id": 0,
  "boardTitle": null,
  "placeName": null,
  "player": 0,
  "remark": null
}
```

## 解説

* variations - バリエーション情報
  * variation_id - バリエーションを識別するユニークなID

## エラーレスポンスの例