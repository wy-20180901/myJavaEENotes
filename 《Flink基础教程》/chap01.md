[toc]

# 为何选择Flink

- **数据流**指由连续数据组成的流， **流数据**是指数据流中的数据。

- `Lambda`架构：通过批量`MapReduce`作业提供了虽有延迟但是结果准确的计算，同时通过`Storm`将最新数据的结果初步展示出来。

  缺点：

  基于`MapReduce`和`HDFS`的`lambda`系统有一个长达数小时的时间窗口，在这个窗口内，由于实时任务失败而产生的不准确结果会一直存在。并且这种系统难以维护。

- 若要依靠多个流来计算结果，必须将数据从一个事件保留到下一个事件，这些保存下来的数据叫做计算状态，准确处理状态对于计算结果的一致性至关重要，在故障或中断之后能够准确地更新状态的容错的关键。

- `Flink`的理念：`Apache Flink`是为分布式、高性能、随时可用以及准确的流处理应用程序打造的开源处理框架。

  同时支持高吞吐和`exactly-once`语义的实时计算，提供批量数据处理。

  ![image-20210428182722280](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/182724-422896.png)

- `Flink`分别提供了面向流处理的接口`(DataStream API)`和面向批处理的接口`(DataSet API)`。因此，`Flink`既可以完成流处理，也可以完成批处理。

  ![image-20210428184403668](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/184403-303770.png)

