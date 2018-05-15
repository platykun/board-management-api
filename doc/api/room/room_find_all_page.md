# PUT room/find_all/{page}

ルームを検索

## リクエストパラメーター

| Name         | Description                      |
|--------------|----------------------------------|
| page   | ページ番号 (必須)         |

## リクエストの例

`GET http://localhost:8080/room/find_all/0`  

## レスポンスの例

```
[
  {
    "id":0,
    "boardTitle":"ボードゲーム0",
    "placeName":"ボドゲショップ1",
    "player":2,
    "remark":null
  },{
    "id":1,
    "boardTitle":"ボードゲーム0",
    "placeName":"ボドゲショップ1",
    "player":3,"remark":"備考"
  }
]
```

## 解説

* variations - バリエーション情報
  * variation_id - バリエーションを識別するユニークなID

## エラーレスポンスの例