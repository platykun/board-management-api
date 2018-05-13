# PUT user/{id}/create_room

ルームを新規作成

## リクエストパラメーター

| Name         | Description                      |
|--------------|----------------------------------|
| boardTitle   | ボードゲームタイトル (必須)         |
| placeName    | 場所名(必須)                      |
| player       | プレイ人数(必須)                   |
| remark       | 備考 (任意 デフォルト: "")         |

## リクエストの例

`PUT http://localhost:8080/user/1/create_room`  

```
{
  "boardTitle":"ボードゲーム0",
  "placeName":"ボドゲショップ1",
  "player":"3",
  "remark":"備考"
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