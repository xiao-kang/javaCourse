#GC的比较

###串行GC
* 串行GC适合相应时间不严格的场景，实现方式简单。
###并行GC
* 并行GC会在进行GC时使用所有的CPU资源进行GC，一般来每次STW的时间比串行短。
###CMS
* CMS相比并行GC的优点在于可以每次回收一部分垃圾，简单来说，可以理解为增量的垃圾回收机制。
###G1
* G1将内存化为片区，在每个片区内划分老年队，新生代。理论上说，STW的时间会比较短。但是也要防止G1的退化。
