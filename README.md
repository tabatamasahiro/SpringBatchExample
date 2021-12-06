# 特定のJOBの実行

### fooJobの実行
java -jar SpringBatchExample-0.0.1-SNAPSHOT.jar --spring.batch.job.names=aaa

### barJobの実行
java -jar SpringBatchExample-0.0.1-SNAPSHOT.jar --spring.batch.job.names=bbb

### コマンドラインパラメータ指定で起動
java -jar SpringBatchExample-0.0.1-SNAPSHOT.jar --spring.batch.job.names=bbb　-ccc=xxx

- ``--ccc=xxx``ではなく``-ccc=xxx``もしくは``ccc=xxx``

# 考慮点
### リラン（はじめから実行）

### コマンドライン引数から実行


