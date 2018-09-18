接口列表
--------

|接口类型| **接口数据类型**      | **请求方法** | **类型** | **描述**|   **需要验签**| 
|-------------| ------------- | -------- | ------ | ----------------------------------- |-------- |
|Restful| 市场接口 |  <a href="#1">/v1/contract_contract_info </a>        | GET | 获取合约信息               | 否  |
|Restful| 市场接口 |  <a href="#2">/v1/contract_index </a>                | GET | 获取合约指数信息           |  否 |
|Restful| 市场接口 |  <a href="#3"> /v1/contract_price_limit </a>         | GET | 获取合约最高限价和最低限价 | 否  |
|Restful| 市场接口 |  <a href="#4"> /v1/contract_open_interest</a>         | GET | 获取当前可用合约总持仓量 |否   |
|Restful| 市场接口|   <a href="#5"> /market/depth </a>         | GET |获取行情深度数据  |否   |
|Restful| 市场接口 |  <a href="#6">/market/history/kline  </a>         | GET | 获取K线数据 |否   |
|Restful| 市场接口 |  <a href="#7"> /market/detail/merged </a>         | GET | 获取聚合行情 |否   |
|Restful| 市场接口 |  <a href="#8"> /market/trade </a>         | GET | 获取市场最近成交记录 | 否  |
|Restful| 市场接口 |  <a href="#9">/market/history/trade  </a>         | GET | 批量获取最近的交易记录 | 否  |
|Restful| 资产接口 |  <a href="#101"> /v1/contract_account_info </a>         | POST |获取用户账户信息  | 是  |
|Restful| 资产接口 |  <a href="#102">/v1/contract_position_info  </a>         | POST |获取用户持仓信息  | 是  |
|Restful| 交易接口 |  <a href="#103"> /v1/contract_order </a>         | POST | 合约下单 | 是  |
|Restful| 交易接口 |  <a href="#104">/v1/contract_batchorder   </a>   | POST |合约批量下单  |   是|
|Restful| 交易接口 |  <a href="#105">/v1/contract_cancel </a>         | POST |撤销订单  | 是  |
|Restful| 交易接口 |  <a href="#106">/v1/contract_cancelall  </a>         | POST |全部撤单  |是   |
|Restful| 交易接口 |  <a href="#107">/v1/contract_order_info  </a>         | POST | 获取合约订单信息 | 是  |
|Restful| 交易接口 |  <a href="#108"> /v1/contract_order_detail  </a>         | POST |获取订单明细信息  | 是  |
|Restful| 交易接口 |  <a href="#109"> /v1/contract_openorders  </a>         | POST | 获取合约当前未成交委托 | 是  |
|Restful| 交易接口 |  <a href="#110"> /v1/contract_hisorders  </a>         | POST |获取合约历史委托  | 是  |
|签名认证| 认证方式 | <a href="#199"> 认证方式 </a>         |  |认证方式  | 否  |
|签名认证| 访问次数限制 | <a href="#200"> 访问次数限制 </a>         |  |访问次数限制  |否   |
|Websocket| 市场接口 | <a href="#201"> market.\\$symbol.kline.\\$period</a>         |sub  |订阅 KLine 数据  | 否  |                                 
|Websocket| 市场接口 | <a href="#202"> market.\\$symbol.kline.\\$period</a>         | req |请求 KLine 数据  | 否  |
|Websocket| 市场接口 | <a href="#203"> market.\\$symbol.depth.\\$type </a>         |sub  |订阅 Market Depth 数据  | 否  |
|Websocket| 市场接口 | <a href="#204"> market.\\$symbol.depth.\\$type </a>         | req |请求 Market Depth 数据  |  否 |
|Websocket| 市场接口 | <a href="#205"> market.$symbol.trade.detail </a>         | sub |订阅 Trade Detail 数据  | 否  |
|Websocket| 市场接口 | <a href="#206"> market.$symbol.detail </a>         | req |请求 Trade Detail 数据  |否   |






Restful API
===========


市场接口
--------

#### <a name="1"> 获取合约信息 </a> 

URL /v1/contract_contract_info

**请求参数**

| **参数名称**      | **参数类型** | **必填** | **描述**                                   |
| ------------- | -------- | ------ | ---------------------------------------- |
| symbol        | string   | false      | "BTC","ETH"...                           |
| contract_type | string   | false      | 合约类型: （this_week:当周 next_week:下周 quarter:季度） |
| contract_code | string   | false      | BTC180914                                |

**备注**：
如果不填，默认查询所有所有合约信息;
如果contract_code填了值，那就按照contract_code去查询;
如果contract_code没有填值，则按照symbol+contract_type去查询;

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**           | **取值范围**                                 |
| -------------------- | -------- | ------- | ---------------- | ---------------------------------------- |
| status               | true     | string  | 请求处理结果           | "ok" , "error"                           |
| \<list\>(属性名称: data) |          |         |                  |                                          |
| symbol               | true     | string  | 品种代码             | "BTC","ETH"...                           |
| contract_code        | true     | string  | 合约代码             | "BTC180914" ...                         |
| contract_type        | true     | string  | 合约类型             | 当周:"this_week", 次周:"next_week", 季度:"quarter" |
| contract_size        | true     | decimal | 合约面值，即1张合约对应多少美元 | 10, 100...                               |
| price_tick           | true     | decimal | 合约价格最小变动精度       | 0.001, 0.01...                           |
| delivery_date        | true     | string  | 合约交割日期           | 如"20180720"                              |
| create_date          | true     | string  | 合约上市日期           | 如"20180706"                              |
| contract_status      | true     | int     | 合约状态             | 合约状态: 0:已下市、1:上市、2:待上市、3:停牌，4:暂停上市中、5:结算中、6:交割中、7:结算完成、8:交割完成、9:暂停上市 |
| \</list\>            |          |         |                  |                                          |
| ts                   | true     | long    | 响应生成时间点，单位：毫秒    |                                          |

**示例**
```
GET http://api.hbdm.com/api/v1/contract_contract_info

# Response
{
  "status": "ok",
  "data": [
    {
      "symbol": "BTC",
      "contract_code": "BTC180914",
      "contract_type": "this_week",
      "contract_size": 100,
      "price_tick": 0.001,
      "delivery_date": "20180704",
      "create_date": "20180604",
      "contract_status": 1
     }
    ],
  "ts":158797866555
}

```
#### <a name="2">获取合约指数信息 </a>

URL  /v1/contract_index

**请求参数**

| **参数名称** | **参数类型** | **必填** | **描述**         |
| -------- | -------- | ------ | -------------- |
| symbol   | string   | true      | "BTC","ETH"... |

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**        | **取值范围**       |
| -------------------- | -------- | ------- | ------------- | -------------- |
| status               | true     | string  | 请求处理结果        | "ok" , "error" |
| \<list\>(属性名称: data) |          |         |               |                |
| symbol               | true     | string  | 指数代码          | "BTC","ETH"... |
| index_price          | true     | decimal | 指数价格          |                |
| \</list\>            |          |         |               |                |
| ts                   | true     | long    | 响应生成时间点，单位：毫秒 |                |

**示例**
```
GET  http://api.hbdm.com/api/v1/contract_index?symbol=BTC

# Response
{
  "status":"ok",
  "data": [
     {
       "symbol": "BTC",
       "index_price":471.0817
      }
    ],
  "ts": 1490759594752
}
```
#### <a name="3">获取合约最高限价和最低限价</a>

URL /v1/contract_price_limit

**请求参数**

| **参数名称**      | **参数类型** | **必填** | **描述**                                   |
| ------------- | -------- | ------ | ---------------------------------------- |
| symbol        | string   | false      | "BTC","ETH"...                           |
| contract_type | string   | false      | 合约类型 (当周:"this_week", 次周:"next_week", 季度:"quarter") |
| contract_code | string   | false      | BTC180914  ...                                |

**备注**：如果contract_code填了值，那就按照contract_code去查询，如
contract_code没有填值，

则按照symbol+contract_type去查询，两个查询条件必填一个

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**        | **取值范围**                                 |
| -------------------- | -------- | ------- | ------------- | ---------------------------------------- |
| status               | true     | string  | 请求处理结果        | "ok" ,"error"                            |
| \<list\>(属性名称: data) |          |         |               |                                          |
| symbol               | true     | string  | 品种代码          | "BTC","ETH" ...             |
| high_limit           | true     | decimal | 最高买价          |                                          |
| low_limit            | true     | decimal | 最低卖价          |                                          |
| contract_code        | true     | string  | 合约代码          | 如"BTC180914"  ...                             |
| contract_type        | true     | string  | 合约类型          | 当周:"this_week", 次周:"next_week", 季度:"quarter" |
| \<list\>             |          |         |               |                                          |
| ts                   | true     | long    | 响应生成时间点，单位：毫秒 |                                          |

**示例**
```
GET  http://api.hbdm.com/api/v1/contract_price_limit?symbol=BTC&contract_type=this_week

# Response
{
  "status":"ok",
  "data": 
    {
      "symbol":"BTC",
      "high_limit":443.07,
      "low_limit":417.09,
      "contract_code":"BTC180914",
      "contract_type":"this_week"
     },
  "ts": 1490759594752
}
```
#### <a name="4">获取当前可用合约总持仓量 </a>

URL /v1/contract_open_interest

**请求参数**

| **参数名称**      | **参数类型** | **必填** | **描述**                                   |
| ------------- | -------- | ------ | ---------------------------------------- |
| symbol        | string   | false      | "BTC","ETH"...                           |
| contract_type | string   | false      | 合约类型 (当周:"this_week", 次周:"next_week", 季度:"quarter") |
| contract_code | string   | false      | BTC180914                                  |

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**        | **取值范围**                                 |
| -------------------- | -------- | ------- | ------------- | ---------------------------------------- |
| status               | true     | string  | 请求处理结果        | "ok" , "error"                           |
| \<list\>(属性名称: data) |          |         |               |                                          |
| symbol               | true     | string  | 品种代码          | "BTC", "ETH" ...           |
| contract_type        | true     | string  | 合约类型          | 当周:"this_week", 次周:"next_week", 季度:"quarter" |
| volume               | true     | decimal | 持仓量(张)        |                                          |
| amount               | true     | decimal | 持仓量(币)        |                                          |
| contract_code        | true     | string  | 合约代码          | 如"BTC180914"   ...                            |
| \</list\>            |          |         |               |                                          |
| ts                   | true     | long    | 响应生成时间点，单位：毫秒 |                                          |

**示例**
```
GET  http://api.hbdm.com/api/v1/contract_open_interest?symbol=BTC&contract_type=this_week

# Response
{
  "status":"ok",
  "data":
    {
      "symbol":"BTC",
      "contract_type": "this_week",
      "volume":123,
      "amount":106,
      "contract_code": "BTC180914"
     },
  "ts": 1490759594752
}
```
#### <a name="5">获取行情深度数据</a>

URL /market/depth

**请求参数**

| **参数名称** | **参数类型** | **必填** | **描述**                                   |
| -------- | -------- | ------ | ---------------------------------------- |
| symbol   | string   | true      | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |
| type     | string   | true      | step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度 |

**返回参数**

| **参数名称** | **是否必须** | **数据类型** | **描述**                                  | **取值范围**       |
| -------- | -------- | -------- | --------------------------------------- | -------------- |
| ch       | true     | string   | 数据所属的 channel，格式： market.period         |                |
| status   | true     | string   | 请求处理结果                                  | "ok" , "error" |
| asks     | true     | object   | 卖盘,[price(挂单价), vol(此价格挂单张数)], 按price升序 |                |
| bids     | true     | object   | 买盘,[price(挂单价), vol(此价格挂单张数)], 按price降序 |                |
| ts       | true     | number   | 响应生成时间点，单位：毫秒                           |                |
```
tick 说明:

"tick": {
  "id": 消息id.
  "ts": 消息生成时间，单位：毫秒.
  "bids": 买盘,[price(挂单价), vol(此价格挂单张数)], //按price降序.
  "asks": 卖盘,[price(挂单价), vol(此价格挂单张数)]  //按price升序.
}
```
**示例**
```
GET http:///api.hbdm.com/market/depth?symbol=BTC_CQ&type=step5

# Response
{
  "ch":"market.BTC_CQ.depth.step5",
  "status":"ok",
    "tick":{
      "asks":[
        [6580,3000],
        [70000,100]
        ],
      "bids":[
        [10,3],
        [2,1]
        ],
      "ch":"market.BTC_CQ.depth.step5",
      "id":1536980854,
      "mrid":6903717,
      "ts":1536980854171,
      "version":1536980854
    },
  "ts":1536980854585
}
```
#### <a name="6">获取K线数据</a>

URL
/market/history/kline

**请求参数**

| **参数名称** | **是否必须** | **类型**  | **描述** | **默认值** | **取值范围**                                 |
| -------- | -------- | ------- | ------ | ------- | ---------------------------------------- |
| symbol   | true     | string  | 合约名称   |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |
| period   | true     | string  | K线类型   |         | 1min, 5min, 15min, 30min, 60min, 1hour,4hour,1day, 1mon |
| size     | false    | integer | 获取数量   | 150     | [1,2000]                                 |

**返回参数**

| **参数名称** | **是否必须** | **数据类型** | **描述**                          | **取值范围**       |
| -------- | -------- | -------- | ------------------------------- | -------------- |
| ch       | true     | string   | 数据所属的 channel，格式： market.period |                |
| data     | true     | object   | KLine 数据                        |                |
| status   | true     | string   | 请求处理结果                          | "ok" , "error" |
| ts       | true     | number   | 响应生成时间点，单位：毫秒                   |                |

Data说明：
```
"data": [
  {
    "id": K线id,
    "vol": 成交量(张),
    "count": 成交笔数,
    "open": 开盘价,
    "close": 收盘价,当K线为最晚的一根时，是最新成交价
    "low": 最低价,
    "high": 最高价,
    "amount": 成交量(币), 即 sum(每一笔成交量(张)*单张合约面值/该笔成交价)
   }
]
```
**示例**
```
GET  http://api.hbdm.com/market/history/kline?period=1min&size=200&symbol=BTC_CQ

# Response
{
  "ch": "market.BTC_CQ.kline.1min",
  "data": [
    {
      "vol": 2446,
      "close": 5000,
      "count": 2446,
      "high": 5000,
      "id": 1529898120,
      "low": 5000,
      "open": 5000,
      "amount": 48.92
     },
    {
      "vol": 0,
      "close": 5000,
      "count": 0,
      "high": 5000,
      "id": 1529898780,
      "low": 5000,
      "open": 5000,
      "amount": 0
     }
   ],
  "status": "ok",
  "ts": 1529908345313
}


```
####  <a name="7">获取聚合行情</a>

URL
/market/detail/merged

**请求参数**

| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | ------ | ------- | ---------------------------------------- |
| symbol   | true     | string | 合约名称   |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |

**返回参数**

| **参数名称** | **是否必须** | **数据类型** | **描述**                                   | **取值范围**       |
| -------- | -------- | -------- | ---------------------------------------- | -------------- |
| ch       | true     | string   | 数据所属的 channel，格式： market.$symbol.detail.merged |                |
| status   | true     | string   | 请求处理结果                                   | "ok" , "error" |
| tick     | true     | object   | K线数据                                     |                |
| ts       | true     | number   | 响应生成时间点，单位：毫秒                            |                |

tick说明:
```
"tick": {
  "id": K线id,
  "vol": 成交量（张）,
  "count": 成交笔数,
  "open": 开盘价,
  "close": 收盘价,当K线为最晚的一根时，是最新成交价
  "low": 最低价,
  "high": 最高价,
  "amount": 成交量(币), 即 sum(每一笔成交量(张)*单张合约面值/该笔成交价)
  "bid": [买1价,买1量(张)],
  "ask": [卖1价,卖1量(张)]
  }
```
**示例**
```
GET  http:api.hbdm.com/market/detail/merged?symbol=BTC_CQ

# Response
{
  "ch": "market.BTC_CQ.detail.merged",
  "status": "ok",
  "tick": 
    {
      "vol":"13305",
      "ask": [5001, 2],
      "bid": [5000, 1],
      "close": "5000",
      "count": "13305",
      "high": "5000",
      "id": 1529387841,
      "low": "5000",
      "open": "5000",
      "ts": 1529387842137,
      "amount": "266.1"
     },
  "ts": 1529387842137
}
```
#### <a name="8">获取市场最近成交记录</a>

URL /market/trade

**请求参数**

| **参数名称** | **是否必须** | **类型** | **描述**    | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | --------- | ------- | ---------------------------------------- |
| symbol   | true     | string | 合约名称      |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |

**返回参数**

| **参数名称** | **是否必须** | **类型** | **描述**                                   | **默认值** | **取值范围**     |
| -------- | -------- | ------ | ---------------------------------------- | ------- | ------------ |
| ch       | true     | string | 数据所属的 channel，格式： market.$symbol.trade.detail |         |              |
| status   | true     | string |                                          |         | "ok","error" |
| tick     | true     | object | Trade 数据                                 |         |              |
| ts       | true     | number | 发送时间                                     |         |              |

Tick说明：
```
"tick": {
  "id": 消息id,
  "ts": 最新成交时间,
  "data": [
    {
     "id": 成交id,
     "price": 成交价钱,
     "amount": 成交量(张),
     "direction": 主动成交方向,
     "ts": 成交时间
    }
  ]
}
```


**示例**
```
GET  http://api.hbdm.com/market/trade?symbol=BTC_CQ

# Response
{
  "ch": "market.BTC_CQ.trade.detail",
  "status": "ok",
  "tick": {
    "data": [
      {
        "amount": "1",
        "direction": "sell",
        "id": 6010881529486944176,
        "price": "5000",
        "ts": 1529386945343
       }
     ],
    "id": 1529388202797,
    "ts": 1529388202797
    },
  "ts": 1529388202797
}
```
#### <a name="9">批量获取最近的交易记录</a>

URL /market/history/trade

**请求参数：**

| **参数名称** | **是否必须** | **数据类型** | **描述**    | **默认值** | **取值范围**                                 |
| -------- | -------- | -------- | --------- | ------- | ---------------------------------------- |
| symbol   | true     | string   | 合约名称      |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |
| size     | false    | number   | 获取交易记录的数量 | 1       | [1, 2000]                                |

**返回参数**

| **参数名称** | **是否必须** | **数据类型** | **描述**                                   | **取值范围**     |
| -------- | -------- | -------- | ---------------------------------------- | ------------ |
| ch       | true     | string   | 数据所属的 channel，格式： market.$symbol.trade.detail |              |
| data     | true     | object   | Trade 数据                                 |              |
| status   | true     | string   |                                          | "ok"，"error" |
| ts       | true     | number   | 响应生成时间点，单位：毫秒                            |              |

data说明：
```
"data": {
  "id": 消息id,
  "ts": 最新成交时间,
  "data": [
    {
      "id": 成交id,
      "price": 成交价,
      "amount": 成交量(张),
      "direction": 主动成交方向,
      "ts": 成交时间
    }
  ]
}
```


**示例**
```
GET  http:///www.hbdm.com/api/v1

# Response
{
  "ch": "market.BTC_CQ.trade.detail",
  "status": "ok",
  "ts": 1529388050915,
  "data": [
    {
      "id": 601088,
      "ts": 1529386945343,
      "data": [
        {
         "amount": 1,
         "direction": "sell",
         "id": 6010881529486944176,
         "price": 5000,
         "ts": 1529386945343
         }
       ]
    }
   ]
}

```


签名认证
--------

#### <a name="199">认证方式</a>

用户私有接口(资产接口、交易接口)跟用户相关的所有接口都需加密验签，签证用户合法性

签名认证方式跟pro现货签名一致，详情请参考https://github.com/huobiapi/API_Docs/wiki/REST_authentication

#### <a name="200">访问次数限制</a>

公开行情接口和用户私有接口都有访问次数限制，公开行情接口每秒访问上限10次，用户私有接口每秒访问上限5次


资产接口
--------

#### <a name="101"> 获取用户账户信息</a>

URL  /v1/contract_account_info 

**请求参数**

| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                    |
| -------- | -------- | ------ | ------ | ------- | --------------------------- |
| symbol   | false    | string | 品种代码   |         | "BTC","ETH"...如果缺省，默认返回所有品种 |

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**               | **取值范围**       |
| -------------------- | -------- | ------- | -------------------- | -------------- |
| status               | true     | string  | 请求处理结果               | "ok" , "error" |
| \<list\>(属性名称: data) |          |         |                      |                |
| symbol               | true     | string  | 品种代码                 | "BTC","ETH"... |
| margin_balance       | true     | decimal | 账户权益                 |                |
| margin_position      | true     | decimal | 持仓保证金（当前持有仓位所占用的保证金） |                |
| margin_frozen        | true     | decimal | 冻结保证金                |                |
| margin_available     | true     | decimal | 可用保证金                |                |
| profit_real          | true     | decimal | 已实现盈亏                |                |
| profit_unreal        | true     | decimal | 未实现盈亏                |                |
| risk_rate            | true     | decimal | 保证金率                 |                |
| liquidation_price    | true     | decimal | 预估爆仓价                |                |
| available_withdraw   | true     | decimal | 可划转数量            |                |
| lever_rate           | true     | decimal | 杠杠倍数           |                |
| \</list\>            |          |         |                      |                |
| ts                   | number   | long    | 响应生成时间点，单位：毫秒        |                |

**示例**
```
POST  http://api.hbdm.com/api/v1/contract_account_info    

# Response
{
  "status": "ok",
  "data": [
    {
      "symbol": "BTC",
      "margin_balance": 1,
      "margin_position": 0,
      "margin_frozen": 3.33,
      "margin_available": 0.34,
      "profit_real": 3.45,
      "profit_unreal": 7.45,
	  "available_withdraw":4.0989898,
      "risk_rate": 100,
      "liquidation_price": 100
     },
    {
      "symbol": "ETH",
      "margin_balance": 1,
      "margin_position": 0,
      "margin_frozen": 3.33,
      "margin_available": 0.34,
      "profit_real": 3.45,
      "profit_unreal": 7.45,
	  "available_withdraw":4.7389859,
      "risk_rate": 100,
      "liquidation_price": 100
     }
   ],
  "ts":158797866555
}

```
#### <a name="102">获取用户持仓信息</a>

URL /v1/contract_position_info

**请求参数**

| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                    |
| -------- | -------- | ------ | ------ | ------- | --------------------------- |
| symbol   | false    | string | 品种代码   |         | "BTC","ETH"...如果缺省，默认返回所有品种 |

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**        | **取值范围**       |
| -------------------- | -------- | ------- | ------------- | -------------- |
| status               | true     | string  | 请求处理结果        | "ok" , "error" |
| \<list\>(属性名称: data) |          |         |               |                |
| symbol               | true     | string  | 品种代码          | "BTC","ETH"... |
| contract_code        | true     | string  | 合约代码          |  "BTC180914" ...  |
| contract_type        | true     | string  | 合约类型          | 当周:"this_week", 次周:"next_week", 季度:"quarter" |
| volume               | true     | decimal | 持仓量           |                |
| available            | true     | decimal | 可平仓数量         |                |
| frozen               | true     | decimal | 冻结数量          |                |
| cost_open            | true     | decimal | 开仓均价          |                |
| cost_hold            | true     | decimal | 持仓均价          |                |
| profit_unreal        | true     | decimal | 未实现盈亏         |                |
| profit_rate          | true     | decimal | 收益率           |                |
| profit               | true     | decimal | 收益            |                |
| position_margin      | true     | decimal | 持仓保证金         |                |
| lever_rate           | true     | int     | 杠杠倍数          |                |
| direction            | true     | string  | "buy":买 "sell":卖         |                |
| \</list\>            |          |         |               |                |
| ts                   | true     | long    | 响应生成时间点，单位：毫秒 |                |

**示例**
```
POST  http://api.hbdm.com/api/v1/contract_position

# Response
{
  "status": "ok",
  "data": [
    {
      "symbol": "BTC",
      "contract_code": "BTC180914",
      "contract_type": "this_week",
      "volume": 1,
      "available": 0,
      "frozen": 0.3,
      "cost_open": 422.78,
      "cost_hold": 422.78,
      "profit_unreal": 0.00007096,
      "profit_rate": 0.07,
      "profit": 0.97,
      "position_margin": 3.4,
      "lever_rate": 10,
      "direction":"buy"
     }
    ],
 "ts": 158797866555
}
```


交易接口
--------

#### <a name="103">合约下单 </a>

URL /v1/contract_order

**请求参数**

| **参数名**          | **参数类型** | **必填** | **描述**                                   |
| ---------------- | -------- | ------ | ---------------------------------------- |
| symbol           | string   | false      | "BTC","ETH"...                           |
| contract_type    | string   | false      | 合约类型 ("this_week":当周 "next_week":下周 "quarter":季度) |
| contract_code    | string   | false      | BTC180914                                  |
| client_order_id  | long     | false      | 客户自己填写和维护，这次一定要大于上一次                     |
| price            | decimal  | true      | 价格                                       |
| volume           | long     | true      | 委托数量(张)                                  |
| direction        | string   | true      | "buy":买 "sell":卖                         |
| offset           | string   | true      | "open":开 "close":平                       |
| lever_rate       | int      | true      | 杠杆倍数[“开仓”若有10倍多单，就不能再下20倍多单]             |
| order_price_type | string   | true      | 订单报价类型 "limit":限价 "opponent":对手价                |

**备注**：如果contract_code填了值，那就按照contract_code去下单，如果contract_code没有填值，则按照symbol+contract_type去下单。

**返回参数**

| **参数名称**        | **是否必须** | **类型** | **描述**                 | **取值范围**       |
| --------------- | -------- | ------ | ---------------------- | -------------- |
| status          | true     | string | 请求处理结果                 | "ok" , "error" |
| order_id        | true     | long   | 订单ID                   |                |
| client_order_id | true     | long   | 用户下单时填写的客户端订单ID，没填则不返回 |                |
| ts              | true     | long   | 响应生成时间点，单位：毫秒          |                |

**示例**
```
POST  http://api.hbdm.com/api/v1/contract_order

# Response
{
  "status": "ok",
  "order_id": 986,
  "client_order_id": 9086,
  "ts": 158797866555
}
```
#### <a name="104">合约批量下单 </a>

URL /v1/contract_batchorder

**请求参数**

| **参数名**                     | **参数类型** | **必填** | **描述**                                   |
| --------------------------- | -------- | ------ | ---------------------------------------- |
| \<list\>(属性名称: orders_data) |          |        |                                          |
| symbol                      | string   | false      | "BTC","ETH"...                           |
| contract_type               | string   | false      | 合约类型: "this_week":当周 "next_week":下周 "quarter":季度 |
| contract_code               | string   | false      | BTC180914                                  |
| client_order_id             | long     | true      | 客户自己填写和维护，这次一定要大于上一次                     |
| price                       | decimal  | true      | 价格                                       |
| volume                      | long     | true      | 委托数量(张)                                  |
| direction                   | string   | true      | "buy":买 "sell":卖                         |
| offset                      | string   | true      | "open":开 "close":平                       |
| lever_rate                  | int      | true      | 杠杆倍数[“开仓”若有10倍多单，就不能再下20倍多单]             |
| order_price_type            | string   | true      |订单报价类型 "limit":限价 "opponent":对手价               |
| \</list\>                   |          |        |                                          |

**备注**：如果contract_code填了值，那就按照contract_code去下单，如果contract_code没有填值，则按照symbol+contract_type去下单。

**返回参数**

| **参数名称**                | **是否必须** | **类型** | **描述**                 | **取值范围**       |
| ----------------------- | -------- | ------ | ---------------------- | -------------- |
| status                  | true     | string | 请求处理结果                 | "ok" , "error" |
| \<list\>(属性名称: errors)  |          |        |                        |                |
| index                   | true     | int    | 订单索引                   |                |
| err_code                | true     | int    | 错误码                    |                |
| err_msg                 | true     | string | 错误信息                   |                |
| \</list\>               |          |        |                        |                |
| \<list\>(属性名称: success) |          |        |                        |                |
| index                   | true     | int    | 订单索引                   |                |
| order_id                | true     | long   | 订单ID                   |                |
| client_order_id         | true     | long   | 用户下单时填写的客户端订单ID，没填则不返回 |                |
| \</list\>               |          |        |                        |                |
| ts                      | true     | long   | 响应生成时间点，单位：毫秒          |                |

**示例**
```
POST  http:///api.hbdm.com/api/v1/contract_batchorder

# Response
{
  "status": "ok",
  "data": {
    "errors":[
      {
        "index":0,
        "err_code": 200417,
        "err_msg": "invalid symbol"
       },
      {
        "index":3,
        "err_code": 200415,
        "err_msg": "invalid symbol"
       }
     ],
    "success":[
      {
        "index":1,
        "order_id":161256,
        "client_order_id":1344567
       },
      {
        "index":2,
        "order_id":161257,
        "client_order_id":1344569
       }
     ]
   },
  "ts": 1490759594752
}
```
#### <a name="105">撤销订单 </a>

URL /v1/contract_cancel

**请求参数**

| **参数名称**        | **是否必须** | **类型** | **描述**                               |
| --------------- | -------- | ------ | ------------------------------------ |
| order_id        | false    | string | 订单ID（ 多个订单ID中间以","分隔,一次最多允许撤消50个订单 ） |
| client_order_id | false    | string | 客户订单ID(多个订单ID中间以","分隔,一次最多允许撤消50个订单) |

**备注**：
order_id和client_order_id都可以用来撤单，同时只可以设置其中一种，如果设置了两种，默认以order_id来撤单。

**返回参数**

| **参数名称**               | **是否必须** | **类型** | **描述**                             | **取值范围**       |
| ---------------------- | -------- | ------ | ---------------------------------- | -------------- |
| status                 | true     | string | 请求处理结果                             | "ok" , "error" |
| \<list\>(属性名称: errors) |          |        |                                    |                |
| order_id               | true     | string | 订单ID                               |                |
| err_code               | true     | int    | 错误码                                |                |
| err_msg                | true     | string | 错误信息                               |                |
| \</list\>              |          |        |                                    |                |
| successes              | true     | string | 撤销成功的订单的order_id或client_order_id列表 |                |
| ts                     | true     | long   | 响应生成时间点，单位：毫秒                      |                |

**示例**
```
POST  http:///api.hbdm.com.com/api/v1/contract_cancel

# Response
#多笔订单返回结果(成功订单ID,失败订单ID)
{
  "status": "ok",
  "errors":[
    {
      "order_id":161251,
      "err_code": "1002",
      "err_msg": "订单不存在"
     },
    {
      "order_id":161253,
      "err_code": "1002",
      "err_msg": "订单不存在"
     }
   ],
  "success":["161256","1344567"],
  "ts": 1490759594752
}
```
#### <a name="106">全部撤单 </a>

URL /v1/contract_cancelall

**请求参数**

| **参数名称** | **是否必须** | **类型** | **描述**               |
| -------- | -------- | ------ | -------------------- |
| symbol   | true     | string | 品种代码，如"BTC","ETH"... |

**返回参数**

| **参数名称**               | **是否必须** | **类型** | **描述**        | **取值范围**       |
| ---------------------- | -------- | ------ | ------------- | -------------- |
| status                 | true     | string | 请求处理结果        | "ok" , "error" |
| successes              | true     | string | 成功的订单         |                |
| \<list\>(属性名称: errors) |          |        |               |                |
| order_id               | true     | String | 订单id          |                |
| err_code               | true     | int    | 订单失败错误码       |                |
| err_msg                | true     | int    | 订单失败信息        |                |
| \</list\>              |          |        |               |                |
| successes              | true     | string | 成功的订单         |                |
| ts                     | true     | long   | 响应生成时间点，单位：毫秒 |                |

**示例**
```
POST  http://api.hbdm.com.com/api/v1/contract_cancel      

# Response
{
 "symbol": "BTC"
}
# Response
#多笔订单返回结果(成功订单ID,失败订单ID)
{
  "status": "ok",
  "data": {
    "errors":[
      {
        "order_id":"161251",
        "err_code": 200417,
        "err_msg": "invalid symbol"
       },
      {
        "order_id":161253,
        "err_code": 200415,
        "err_msg": "invalid symbol"
       }
      ],
    "successes":[161256,1344567]
   },
  "ts": 1490759594752
}
错误：
{
  "status": "error",
  "err_code": 20012,
  "err_msg": "invalid symbol",
  "ts": 1490759594752
}
```
#### <a name="107">获取合约订单信息</a>

URL /v1/contract_order_info

**请求参数**

| **参数名称**        | **是否必须** | **类型** | **描述**                               |
| --------------- | -------- | ------ | ------------------------------------ |
| order_id        | false    | string | 订单ID（ 多个订单ID中间以","分隔,一次最多允许查询20个订单 ） |
| client_order_id | false    | string | 客户订单ID(多个订单ID中间以","分隔,一次最多允许查询20个订单) |

**备注**：order_id和client_order_id都可以用来查询，同时只可以设置其中一种，如果设置了两种，默认以order_id来查询。

**返回数据**

| **参数名称**             | **是否必须** | **类型**  | **描述**                                   | **取值范围**       |
| -------------------- | -------- | ------- | ---------------------------------------- | -------------- |
| status               | true     | string  | 请求处理结果                                   | "ok" , "error" |
| \<list\>(属性名称: data) |          |         |                                          |                |
| symbol               | true     | string  | 品种代码                                     |                |
| contract_type        | true     | string  | 合约类型                                     | 当周:"this_week", 周:"next_week", 季度:"quarter"  |
| contract_code        | true     | string  | 合约代码                                     |"BTC180914" ...       |
| volume               | true     | decimal | 委托数量                                     |                |
| price                | true     | decimal | 委托价格                                     |                |
| order_price_type     | true     | string  | 订单报价类型 "limit":限价 "opponent":对手价                       |                |
| direction            | true     | string  | "buy":买 "sell":卖                             |                |
| offset               | true     | string  | "open":开 "close":平                              |                |
| lever_rate           | true     | int     | 杠杆倍数                                     | 1\\5\\10\\20   |
| order_id             | true     | long    | 订单ID                                     |                |
| client_order_id      | true     | long    | 客户订单ID                                   |                |
| created_at           | true     | long    | 成交时间                                     |                |
| trade_volume         | true     | decimal | 成交数量                                     |                |
| trade_turnover       | true     | decimal | 成交总金额                                    |                |
| fee                  | true     | decimal | 手续费                                      |                |
| trade_avg_price      | true     | decimal | 成交均价                                     |                |
| margin_frozen        | true     | decimal | 冻结保证金                                    |                |
| profit               | true     | decimal | 收益                                       |                |
| status               | true     | int     | 订单状态(1准备提交 2已定序 3已提交 4部分成交 5部分成交已撤单 6全部成交 7已撤单 11撤单中) |                |
| order_source         | true     | string  | 订单来源（1:系统订单、2:用户网页订单、3:用户API订单、4:用户APP订单 5爆仓来源、6交割来源） |                |
| \</list\>            |          |         |                                          |                |
| ts                   | true     | long    | 时间戳                                      |                |

**示例**
```
POST  http://api.hbdm.com.com/api/v1/contract_order_info

# Response
{
  "status": "ok",
  "data":[
    {
      "symbol": "BTC",
      "contract_type": "this_week",
      "contract_code": "BTC180914",
      "volume": 111,
      "price": 1111,
      "order_price_type": "limit",
      "direction": "buy",
      "offset": "open",
      "lever_rate": 10,
      "order_id": 106837,
      "client_order_id": 10683,
      "order_source": "web",
      "created_at": 1408076414000,
      "trade_volume": 1,
      "trade_turnover": 1200,
      "fee": 0,
      "trade_avg_price": 10,
      "margin_frozen": 10,
      "profit ": 10,
      "status": 0
     },
    {
      "symbol": "ETH",
      "contract_type": "this_week",
      "contract_code": "ETH180921",
      "volume": 111,
      "price": 1111,
      "order_price_type": "limit",
      "direction": "buy",
      "offset": "open",
      "lever_rate": 10,
      "order_id": 106837,
      "client_order_id": 10683,
      "order_source": "web",
      "created_at": 1408076414000,
      "trade_volume": 1,
      "trade_turnover": 1200,
      "fee": 0,
      "trade_avg_price": 10,
      "margin_frozen": 10,
      "profit ": 10,
      "status": 0
     }
    ],
  "ts": 1490759594752
}
```
#### <a name="108">获取订单明细信息</a>

URL /v1/contract_order_detail

**请求参数**

| **参数名称**   | **是否必须** | **类型**     | **描述**         |
| ---------- | -------- | ---------- | -------------- |
| symbol     | **true** | **string** | "BTC","ETH"... |
| order_id   | **true** | **long**   | **订单id**       |
| page_index | false    | int        | 第几页,不填第一页      |
| page_size  | false    | int        | 不填默认20，不得多于50  |

**返回数据**

| **参数名称**                | **是否必须** | **类型**  | **描述**             | **取值范围**       |
| ----------------------- | -------- | ------- | ------------------ | -------------- |
| status                  | true     | string  | 请求处理结果             | "ok" , "error" |
| \<object\> (属性名称: data) |          |         |                    |                |
| symbol                  | true     | string  | 品种代码               |                |
| contract_type           | true     | string  | 合约类型               |当周:"this_week", 次周:"next_week", 季度:"quarter" |
| contract_code           | true     | string  | 合约代码               |"BTC180914" ...         |
| lever_rate              | true     | int     | 杠杆倍数               | 1\\5\\10\\20   |
| direction               | true     | string  | "buy":买 "sell":卖        |                |
| offset                  | true     | string  | "open":开 "close":平           |                |
| volume                  | true     | decimal | 委托数量               |                |
| price                   | true     | decimal | 委托价格               |                |
| created_at              | true     | long    | 成交时间               |                |
| order_source            | true     | string  | 订单来源               |                |
| order_price_type        | true     | string  | 订单报价类型 "limit":限价 "opponent":对手价 |                |
| margin_frozen           | true     | decimal | 冻结保证金              |                |
| profit                  | true     | decimal | 收益                 |                |
| total_page              | true     | int     | 总共页数               |                |
| current_page            | true     | int     | 当前页数               |                |
| total_size              | true     | int     | 总条数                |                |
| \<list\> (属性名称: trades) |          |         |                    |                |
| trade_id                | true     | long    | 撮合结果id             |                |
| trade_price             | true     | decimal | 撮合价格               |                |
| trade_volume            | true     | decimal | 成交量                |                |
| trade_turnover          | true     | decimal | 成交金额               |                |
| trade_fee               | true     | decimal | 成交手续费              |                |
| created_at              | true     | long    | 创建时间               |                |
| \</list\>               |          |         |                    |                |
| \</object \>            |          |         |                    |                |
| ts                      | true     | long    | 时间戳                |                |

**示例**
```
POST  http://api.hbdm.com/api/v1/contract_order_detail

# Response
{
  "status": "ok",
  "data":{
    "symbol": "BTC",
    "contract_type": "this_week",
    "contract_code": "BTC180914",
    "volume": 111,
    "price": 1111,
    "order_price_type": "limit",
    "direction": "buy",
    "offset": "open",
    "status": 1,
    "lever_rate": 10,
    "trade_avg_price": 10,
    "margin_frozen": 10,
    "profit": 10,
    "order_id": 106837,
    "order_source": "web",
    "created_at": 1408076414000,
    "trades":[
      {
        "trade_id":112,
        "trade_volume":1,
        "trade_price":123.4555,
        "trade_fee":0.234,
        "trade_turnover":34.123,
        "created_at": 1490759594752
       }
      ],
    "total_page":15,
    "total_size":3,
    "current_page":3
    },
  "ts": 1490759594752
}
错误:
{
 "status":"error",
 "err_code":20029,
 "err_msg": "invalid symbol",
 "ts": 1490759594752
}
```
#### <a name="109">获取合约当前未成交委托 </a>

URL  /v1/contract_openorders

**请求参数**

| **参数名称**   | **是否必须** | **类型** | **描述**        | **默认值** | **取值范围**       |
| ---------- | -------- | ------ | ------------- | ------- | -------------- |
| symbol     | false    | string | 品种代码          |         | "BTC","ETH"... |
| page_index | false    | int    | 页码，不填默认第1页    | 1       |                |
| page_size  | false    | int    | 不填默认20，不得多于50 | 20      |                |

**返回参数**

| **参数名称**             | **是否必须** | **类型**  | **描述**                               | **取值范围**     |
| -------------------- | -------- | ------- | ------------------------------------ | ------------ |
| status               | true     | string  | 请求处理结果                               |              |
| \<list\>(属性名称: data) |          |         |                                      |              |
| symbol               | true     | string  | 品种代码                                 |              |
| contract_type        | true     | string  | 合约类型                                 | 当周:"this_week", 次周:"next_week", 季度:"quarter"  |
| contract_code        | true     | string  | 合约代码                                 |"BTC180914" ...     |
| volume               | true     | decimal | 委托数量                                 |              |
| price                | true     | decimal | 委托价格                                 |              |
| order_price_type     | true     | string  | 订单报价类型 "limit":限价 "opponent":对手价                  |              |
| direction            | true     | string  | "buy":买 "sell":卖                       |              |
| offset               | true     | string  | "open":开 "close":平                             |              |
| lever_rate           | true     | int     | 杠杆倍数                                 | 1\\5\\10\\20 |
| order_id             | true     | long    | 订单ID                                 |              |
| client_order_id      | true     | long    | 客户订单ID                               |              |
| created_at           | true     | long    | 订单创建时间                               |              |
| trade_volume         | true     | decimal | 成交数量                                 |              |
| trade_turnover       | true     | decimal | 成交总金额                                |              |
| fee                  | true     | decimal | 手续费                                  |              |
| trade_avg_price      | true     | decimal | 成交均价                                 |              |
| margin_frozen        | true     | decimal | 冻结保证金                                |              |
| profit               | true     | decimal | 收益                                   |              |
| status               | true     | int     | 订单状态(3未成交 4部分成交 5部分成交已撤单 6全部成交 7已撤单) |              |
| order_source         | true     | string  | 订单来源                                 |              |
| \</list\>            |          |         |                                      |              |
| total_page           | true     | int     | 总页数                                  |              |
| current_page         | true     | int     | 当前页                                  |              |
| total_size           | true     | int     | 总条数                                  |              |
| ts                   | true     | long    | 时间戳                                  |              |

**示例**
```
POST http:///www.hbdm.com/api/v1/contract_openorders

# Response
{
  "status": "ok",
  "data":{
    "orders":[
      {
         "symbol": "BTC",
         "contract_type": "this_week",
         "contract_code": "BTC180914",
         "volume": 111,
         "price": 1111,
         "order_price_type": "limit",
         "direction": "buy",
         "offset": "open",
         "lever_rate": 10,
         "order_id": 106837,
         "client_order_id": 10683,
         "order_source": "web",
         "created_at": 1408076414000,
         "trade_volume": 1,
         "trade_turnover": 1200,
         "fee": 0,
         "trade_avg_price": 10,
         "margin_frozen": 10,
         "status": 1
        }
       ],
    "total_page":15,
    "current_page":3,
    "total_size":3
   },
  "ts": 1490759594752
}
```
#### <a name="110">获取合约历史委托</a>

URL /v1/contract_hisorders

**请求参数**

| **参数名称**    | **是否必须** | **类型** | **描述**        | **默认值** | **取值范围**                                 |
| ----------- | -------- | ------ | ------------- | ------- | ---------------------------------------- |
| symbol      | true     | string | 品种代码          |         | "BTC","ETH"...                           |
| trade_type  | true     | int    | 交易类型          |         | 0:全部,1:买入开多,2: 卖出开空,3: 买入平空,4: 卖出平多,5: 卖出强平,6: 买入强平,7:交割平多,8: 交割平空 |
| type        | true     | int    | 类型            |         | 1:所有订单、2：结束汏订单                           |
| status      | true     | int    | 订单状态          |         | 0:全部,3:未成交, 4: 部分成交,5: 部分成交已撤单,6: 全部成交,7:已撤单 |
| create_date | true     | int    | 日期            |         | 7，90（7天或者90天）                            |
| page_index  | false    | int    | 页码，不填默认第1页    | 1       |                                          |
| page_size   | false    | int    | 不填默认20，不得多于50 | 20      |                                          |

**返回参数**

| **参数名称**               | **是否必须** | **类型**  | **描述**             | **取值范围**     |
| ---------------------- | -------- | ------- | ------------------ | ------------ |
| status                 | true     | string  | 请求处理结果             |              |
| \<object\>(属性名称: data) |          |         |                    |              |
| \<list\>(属性名称: orders) |          |         |                    |              |
| order_id               | true     | long    | 订单ID               |              |
| symbol                 | true     | string  | 品种代码               |              |
| contract_type          | true     | string  | 合约类型               |当周:"this_week", 次周:"next_week", 季度:"quarter" |
| contract_code          | true     | string  | 合约代码               |"BTC180914" ...       |
| lever_rate             | true     | int     | 杠杆倍数               | 1\\5\\10\\20 |
| direction              | true     | string  | "buy":买 "sell":卖         |              |
| offset                 | true     | string  | "open":开 "close":平           |              |
| volume                 | true     | decimal | 委托数量               |              |
| price                  | true     | decimal | 委托价格               |              |
| create_date            | true     | long    | 创建时间               |              |
| order_source           | true     | string  | 订单来源               |              |
| order_price_type       | true     | string  | 订单报价类型 "limit":限价 "opponent":对手价 |              |
| margin_frozen          | true     | decimal | 冻结保证金              |              |
| profit                 | true     | decimal | 收益                 |              |
| trade_volume           | true     | decimal | 成交数量               |              |
| trade_turnover         | true     | decimal | 成交总金额              |              |
| fee                    | true     | decimal | 手续费                |              |
| trade_avg_price        | true     | decimal | 成交均价               |              |
| status                 | true     | int     | 订单状态               |              |
| \</list\>              |          |         |                    |              |
| \</object\>            |          |         |                    |              |
| total_page             | true     | int     | 总页数                |              |
| current_page           | true     | int     | 当前页                |              |
| total_size             | true     | int     | 总条数                |              |
| ts                     | true     | long    | 时间戳                |              |

**示例**
```
POST http://api.hbdm.com/api/v1/contract_hisorders

# Response
{
  "status": "ok",
  "data":{
    "orders":[
      {
        "symbol": "BTC",
        "contract_type": "this_week",
        "contract_code": "BTC180914",
        "volume": 111,
        "price": 1111,
        "order_price_type": "limit",
        "direction": "buy",
        "offset": "open",
        "lever_rate": 10,
        "order_id": 106837,
        "client_order_id": 10683,
        "order_source": "web",
        "created_at": 1408076414000,
        "trade_volume": 1,
        "trade_turnover": 1200,
        "fee": 0,
        "trade_avg_price": 10,
        "margin_frozen": 10,
        "profit": 10,
        "status": 1
      }
     ],
    "total_page":15,
    "current_page":3,
    "total_size":3
    },
  "ts": 1490759594752
}
```


Websocket API
=============


市场接口
--------

####  <a name="201">订阅 KLine 数据</a>

成功建立和 WebSocket API 的连接之后，向 Server 发送如下格式的数据来订阅数据：
```
{
"sub": "market.$symbol.kline.$period",
"id": "id generate by client"
}
```
| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | ------ | ------- | ---------------------------------------- |
| symbol   | true     | string | 交易对    |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |
| period   | true     | string | K线周期   |         | 1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year |

正确订阅请求参数的例子：
```
{
"sub": "market.BTC_CQ.kline.1min",
"id": "id1"
}
```
订阅成功返回数据的例子
```
{
"id": "id1",
"status": "ok",
"subbed": "market.BTC_CQ.kline.1min",
"ts": 1489474081631
}
```
之后每当 KLine 有更新时，client 会收到数据，例子
```
{
 "ch": "market.BTC_CQ.kline.1min",
 "ts": 1489474082831,
 "tick": 
    {
     "id": 1489464480,
     "vol": 100,
     "count": 0,
     "open": 7962.62,
     "close": 7962.62,
     "low": 7962.62,
     "high": 7962.62,
     "amount": 0.3 //单位BTC 币种的数量
    }
}

tick 说明

"tick": 
  {
   "id": K线id,
   "vol": 成交量张数,
   "count": 成交笔数,
   "open": 开盘价,
   "close": 收盘价,当K线为最晚的一根时，是最新成交价
   "low": 最低价,
   "high": 最高价,
   "amount": BTC, 即 sum(每一笔 该合约面值* 该笔的成交量/成交价)
}
```
**错误订阅的列子**

错误订阅(错误的 symbol)
```
{
 "sub": "market.invalidsymbol.kline.1min",
 "id": "id2"
}
```
订阅失败返回数据的例子
```
{
 "id": "id2",
 "status": "error",
 "err-code": "bad-request",
 "err-msg": "invalid topic market.invalidsymbol.kline.1min",
 "ts": 1494301904959
}
```
错误订阅(错误的 topic)
```
{
 "sub": "market.BTC_CQ.kline.3min",
 "id": "id3"
}
```
订阅失败返回数据的例子
```
{
 "id": "id3",
 "status": "error",
 "err-code": "bad-request",
 "err-msg": "invalid topic market.BTC_CQ.kline.3min",
 "ts": 1494310283622
}
```
####  <a name="202">请求 KLine 数据 </a>   

成功建立和 WebSocket API 的连接之后，向Server 发送如下格式的数据来请求数据：
```
{
 "req": "market.$symbol.kline.$period",
 "id": "id generated by client",
 "from": "optional, type: long, 2017-07-28T00:00:00+08:00 至2050-01-01T00:00:00+08:00 之间的时间点，单位：秒",
  "to": "optional, type: long, 2017-07-28T00:00:00+08:00 至2050-01-01T00:00:00+08:00 之间的时间点，单位：秒，必须比 from 大"}
```
| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | ------ | ------- | ---------------------------------------- |
| symbol   | true     | string | 交易对    |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |
| period   | true     | string | K线周期   |         | 1min, 5min, 15min, 30min, 60min, 1hour,4hour,1day, 1mon |
```
[t1, t5] 假设有 t1  ~ t5 的K线：

from: t1, to: t5, return [t1, t5].
from: t5, to: t1, which t5  > t1, return [].
from: t5, return [t5].
from: t3, return [t3, t5].
to: t5, return [t1, t5].
from: t which t3  < t  <t4, return [t4, t5].
to: t which t3  < t  <t4, return [t1, t3].
from: t1 and to: t2, should satisfy 1325347200  < t1  < t2  < 2524579200.
```
**备注**：一次最多300条

请求 KLine 数据请求参数的例子：
```
{
"req": "market.BTC_CQ.kline.1min",
"id": "id4"
}
```
请求成功返回数据的例子：
```
{
 "rep": "market.BTC_CQ.kline.1min",
 "status": "ok",
 "id": "id4",
 "tick": [
   {
    "vol": 100,
    "count": 27,
    "id": 1494478080,
    "open": 10050.00,
    "close": 10058.00,
    "low": 10050.00,
    "high": 10058.00,
    "amount": 175798.757708
   },
   {
    "vol": 300,
    "count": 28,
    "id": 1494478140,
    "open": 10058.00,
    "close": 10060.00,
    "low": 10056.00,
    "high": 10065.00,
    "amount": 158331.348600
   }
 ]
}
```
####  <a name="203">订阅 Market Depth 数据 </a>

成功建立和 WebSocket API 的连接之后，向 Server 发送如下格式的数据来订阅数据：
```
{
"sub": "market.$symbol.depth.$type",
"id": "id generated by client"
}
```
| **参数名称** | **是否必须** | **类型** | **描述**   | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | -------- | ------- | ---------------------------------------- |
| symbol   | true     | string | 交易对      |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约. |
| type     | true     | string | Depth 类型 |         | step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度 |

**备注**：用户选择“合并深度”时，一定报价精度内的市场挂单将予以合并显示。合并深度仅改变显示方式，不改变实际成交价格。

正确订阅请求参数的例子：
```
{
"sub": "market.BTC_CQ.depth.step0",
"id": "id5"
}
```
订阅成功返回数据的例子：
```
{
  "id": "id5",
  "status": "ok",
  "subbed": "market.BTC_CQ.depth.step0",
  "ts": 1489474081631
}
```
之后每当 depth 有更新时，client 会收到数据，例子：
```
{
 "ch": "market.BTC_CQ.depth.step0",
 "ts": 1489474082831,
 "tick":
   {
     "bids": [
       [9999.9101,1], 
       [9992.3089,2]
       ],
     "asks": [
       [10010.9800,10],
       [10011.3900,15]
       ]
    }
}

tick 说明：
"tick": {
  "bids": [
    [买1价,买1量]
    [买2价,买2量]
     //more data here
   ]
   "asks": [
    [卖1价,卖1量]
    [卖2价,卖2量]
    //more data here
   ]
}
```
####  <a name="204">请求 Market Depth 数据 </a>

成功建立和 WebSocket API 的连接之后，向 Server 发送如下格式的数据来请求数据：
```
{
 "req": "market.$symbol.depth.$type",
 "id": "3123213324"
}
```
| **参数名称** | **是否必须** | **类型** | **描述**   | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | -------- | ------- | ---------------------------------------- |
| symbol   | true     | string | 交易对      |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |
| type     | true     | string | Depth 类型 |         | step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度 |

请求 Market Depth 数据请求参数的例子：
```
{
 "req": "market.BTC_CQ.depth.step0",
 "id": "id6"
}
```
请求成功返回数据的例子：
```
{
 "rep": "market. BTC_CQ.depth.step0",
 "status": "ok",
 "id": "id6",
 "tick": {
   "bids": [
     [9999.9800,1], 
     [9992.9800,3]
    ]
   "asks": [
     [10010.9800,1],
     [10011.3900,5]
    ]
  }
}
```
####  <a name="205">订阅 Trade Detail 数据 </a>

成功建立和 WebSocket API 的连接之后，向 Server 发送如下格式的数据来订阅数据：
```
{
"sub": "market.$symbol.trade.detail",
"id": "id generated by client"
}
```
**备注**：仅能获取最近 300 个 Trade Detail 数据。

| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | ------ | ------- | ---------------------------------------- |
| symbol   | true     | string | 合约名称   |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |

正确订阅请求参数的例子：
```
{
 "sub": "market.BTC_CQ.trade.detail",
 "id": "id7"
}
```
订阅成功返回数据的例子：
```
{
 "id": "id7",
 "status": "ok",
 "subbed": "market.BTC_CQ.trade.detail",
 "ts": 1489474081631
}
```
之后每当 Trade Detail 有更新时，client 会收到数据，例子：
```
{
 "ch": "market.BTC_CQ.trade.detail",
 "ts": 1489474082831,
 "data": [
    {
     "id": 601595424,
     "price": 10195.64,
     "time": 1494495766,
     "amount": 100,
     "direction": "buy",
     "tradeId": 601595424,
     "ts": 1494495766000
    },
    {
     "id": 601595423,
     "price": 10195.64,
     "time": 1494495711,
     "amount": 200,
     "direction": "buy",
     "tradeId": 601595423,
     "ts": 1494495711000
    }
  ]
}

```
data 说明：
```
"data": [
  {
   "id": 消息ID,
   "price": 成交价,
   "time": 成交时间,
   "amount": 成交量（张）,
   "direction": 成交方向,
   "tradeId": 成交ID,
   "ts": 时间戳
  }
 ]
```


####  <a name="206">请求 Market Detail 数据</a>

成功建立和 WebSocket API 的连接之后，向 Server 发送如下格式的数据来请求数据：
```
{
"req": "market.$symbol.detail",
"id": "id generated by client"
}
```
| **参数名称** | **是否必须** | **类型** | **描述** | **默认值** | **取值范围**                                 |
| -------- | -------- | ------ | ------ | ------- | ---------------------------------------- |
| symbol   | true     | string | 合约名称   |         | 如"BTC_CW"表示BTC当周合约，"BTC_NW"表示BTC次周合约，"BTC_CQ"表示BTC季度合约 |

仅返回当前 Market Detail

请求 Market Detail 数据请求参数的例子：
```
{
 "req": "market.BTC_CQ.detail",
 "id": "id8"
}
```
请求成功返回数据的例子：
```
{
 "rep": "d",
 "status": "ok",
 "id": "id8",
 "tick": 
   {
    "vol": 1000,
    "open": 9790.52,
    "close": 10195.00,
    "high": 10300.00,
    "ts": 1494496390000,
    "id": 1494496390,
    "count": 15195,
    "low": 9657.00,
    "amount": 12190.754751
   }
}
```
tick数据说明：
```
"tick":
  {
   "id": K线id,
   "ts": 1494496390000,
   "vol": 成交量(张),
   "count": 成交笔数,
   "open": 开盘价,
   "close": 收盘价,当K线为最晚的一根时，是最新成交价
   "low": 最低价,
   "high": 最高价,
   "amount": 成交量(币), 即 sum(每一笔成交量(张)*单张合约面值/该笔成交价)
}
```


错误代码列表
============

| 错误代码 | 错误描述               |
| ---- | ------------------ |
| 1000 | 系统异常               |
| 1001 | 系统未准备就绪            |
| 1002 | 查询异常               |
| 1003 | 操作redis异常          |
| 1010 | 用户不存在              |
| 1011 | 用户会话不存在            |
| 1012 | 用户账户不存在            |
| 1013 | 合约品种不存在            |
| 1014 | 合约不存在              |
| 1015 | 指数价格不存在            |
| 1016 | 对手价不存在             |
| 1017 | 查询订单不存在            |
| 1030 | 输入错误               |
| 1031 | 非法的报单来源            |
| 1032 | 访问次数超出限制           |
| 1033 | 合约周期字段值错误          |
| 1034 | 报单价格类型字段值错误        |
| 1035 | 报单方向字段值错误          |
| 1036 | 报单开平字段值错误          |
| 1037 | 杠杆倍数不符合要求          |
| 1038 | 报单价格不符合最小变动价       |
| 1039 | 报单价格超出限制           |
| 1040 | 报单数量不合法            |
| 1041 | 报单数量超出限制           |
| 1042 | 超出多头持仓限制           |
| 1043 | 超出多头持仓限制           |
| 1044 | 超出平台持仓限制           |
| 1045 | 杠杆倍数与所持有仓位的杠杆不符合   |
| 1046 | 持仓未初始化             |
| 1047 | 可用保证金不足            |
| 1048 | 持仓量不足              |
| 1049 | 市价单不可以开仓           |
| 1050 | 客户报单号重复            |
| 1051 | 没有可撤订单             |
| 1052 | 超出批量数目限制           |
| 1053 | 无法获取合约的最新价格区间      |
| 1054 | 无法获取合约的最新价         |
| 1055 | 平仓时权益不足            |
| 1056 | 结算中无法下单和撤单         |
| 1057 | 暂停交易中无法下单和撤单       |
| 1058 | 停牌中无法下单和撤单         |
| 1059 | 交割中无法下单和撤单         |
| 1060 | 此合约在非交易状态中，无法下单和撤单 |
| 1061 | 订单不存在，无法撤单         |
| 1062 | 撤单中，无法重复撤单         |
| 1063 | 订单已成交，无法撤单         |
| 1064 | 报单主键冲突             |
| 1065 | 客户报单号不是整数          |
| 1066 | 字段不能为空             |
| 1067 | 字段不合法              |
| 1068 | 导出错误               |
| 1069 | 报单价格不合法            |
| 1100 | 用户没有开仓权限           |
| 1101 | 用户没有平仓权限           |
| 1102 | 用户没有入金权限           |
| 1103 | 用户没有出金权限           |
| 1104 | 合约交易权限,当前禁止交易      |
| 1105 | 合约交易权限,当前只能平仓      |
| 1200 | 登录错误               |
| 1220 | 用户尚未开通合约交易         |
| 1221 | 开户资金不足             |
| 1222 | 开户天数不足             |
| 1223 | 开户VIP等级不足          |
| 1224 | 开户国家限制             |
| 1225 | 开户不成功              |
| 1250 | 无法获取HT_token       |
| 1251 | BTC折合资产无法获取        |
| 1252 | 现货资产无法获取           |
| 1253 | 签名验证错误             |
