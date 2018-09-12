# PUT user/{id}/checkIn/{placeId}

チェックイン情報を記録

## リクエストURL

| Name         | Description                      |
|--------------|----------------------------------|
| id | ユーザID (必須) |
| placeId | 場所ID (必須) |

## リクエストパラメーター

無し

## リクエストの例

`PUT http://localhost:8090/user/1/checkIn/1`  

## レスポンスの例

成功時
レスポンスコード 201
```
{
  "type" : "success",
  "result" : {
    ※未確定
    }
  }
}
```

失敗時
レスポンスコード 200
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

## 解説

* status
  * 記録成功時OK, 失敗時NGを返却
  * すでに記録済の場合上書く仕様。上書いたかどうか、messageに記載

## エラーレスポンスの例