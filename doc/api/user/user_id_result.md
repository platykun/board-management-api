# PUT user/{id}/result

参加ルームの結果を記録

## リクエストURL

| Name         | Description                      |
|--------------|----------------------------------|
| id   | ユーザID (必須)         |
| roomId   | ルームID (必須)         |

## リクエストパラメーター

| Name         | Description                      |
|--------------|----------------------------------|
| userId | ユーザID(任意) |
| rank | 順位 |
| score | スコア |
| comment | コメント |

## リクエストの例

`PUT http://localhost:8090/user/1/result`  

自身の記録を行う場合
```
{
  "userId":"1",
  "rank":"1",
  "score":"100",
  "comment":"勝ちました！"
}
```

他人の記録を行う場合
```
{
  "userId":"2",
  "rank":"1",
  "score":"100",
  "comment":"勝ちました！"
}
```

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
      "rank" : "1"
      "score" : "100"
      "comment" : "勝ちました!"
    }
  }
}
```


## 解説

* status
  * 記録成功時OK, 失敗時NGを返却
  * すでに記録済の場合上書く仕様。上書いたかどうか、messageに記載

## エラーレスポンスの例