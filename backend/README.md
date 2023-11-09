バックエンド資材
=====

# はじめに

マイクロサービスとしてアプリケーションを作成する
データベースは各サービスごとに分割する

# 業務API

| サービス               | 摘要       | port | Language | note                              |
|--------------------|----------|-----:|:---------|:----------------------------------|
| department-service | 部署サービス   | 8081 | Java     | RestController, Jpa               |
| employee-service   | 従業員サービス  | 8082 | Java     | RestController, Jpa, RestTemplate |
| customer-service   | 顧客サービス   | 8084 | Java     | RestController, Jpa, WebClient    |
| ppt-service        | リソースサービス | 8083 | Java     | RestController, Doma2             |

# 業務外サービス

| サービス             | 摘要         | port |
|------------------|------------|-----:|
| service-registry | サービスレジストリー | 8761 |
| api-gateway      | APIゲートウェイ  | 8080 |
| config-service   | コンフィグサーバー  | 8888 |

# application layers

* [root]
    * [domain root]
        * [usecase layer]
            * command
            * query
        * [domain layer]
        * [infra layer]
            * query
            * datasource
            * remoting
            * ...other channel
    * [presentation]
        * web
        * api
        * applicationEvent
        * scheduled
        * ... other bootstrap