# iceworkday
iceworkday工作日算法

工作日算法又称节假日算法。
由于工作日计算涉及农历节气等，而农历算法本身就是非常复杂的，所以工作日算法非常的复杂。
该算法解决了工作日的计算问题。对以往的数据确保其准确性，对将来的数据提供尽可能准确的预测。
由于未来的数据是未知的，为了在将来使该算法对已发生的数据具有准确性，该算法提供了配置文件纠错机制。
该工作日算法的有效计算范围为 2001年--3100年。
对2020年及以前的节假日进行了精确枚举。
WorkUtils.weekendMap(year)方法用于计算节某一年假日map，传入年的字符串。
WorkUtils.isWorkendDay(ymd)方法用于判断某一天是否为节假日，传入年月日字符串。
-------------------------------------------------
|  注意:                                         |
|  该算法对将来的日期仅是预测,并不能绝对准确!             |
-------------------------------------------------
该算法已提交maven中央仓库,
maven依赖如下:<br/>
&lt;dependency&gt;<br/>
&nbsp;&nbsp;&lt;groupId&gt;com.icexxx&lt;/groupId&gt;<br/>
&nbsp;&nbsp;&lt;artifactId&gt;iceworkday&lt;/artifactId&gt;<br/>
&nbsp;&nbsp;&lt;version&gt;2.0.2.0&lt;/version&gt;<br/>
&lt;/dependency&gt;<br/>
maven依赖如下:
<dependency>
    <groupId>com.icexxx</groupId>
    <artifactId>iceworkday</artifactId>
    <version>2.0.2.0</version>
</dependency>
