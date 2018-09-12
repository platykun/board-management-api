# PUT user/{id}/join/{roomId}

ルームに参加

## リクエストURL

| Name         | Description                      |
|--------------|----------------------------------|
| id   | ユーザID (必須)         |
| roomId   | ルームID (必須)         |

## リクエストパラメーター

無し

## リクエストの例

`PUT http://localhost:8090/user/1/join/1`  

## レスポンスの例

成功時
レスポンスコード 201
```
{
  "type" : "success",
  "result" : {
    "joinRoom" : {
      "userId" : "1",
      "roomId" : "1",
      "owner" : "false"
    }
  }
}
```

## 解説

* status
  * 参加成功時：OK, 参加NG時: NGを返す.  
  * 参加がNGの時は失敗時のエラーメッセージを返却する.  

## エラーレスポンスの例