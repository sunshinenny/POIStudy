# POI学习过程以及实现了项目部分功能
## 模板解析以及根据数据库自动生成表格并填入数据
### 思路
1. 读取原始文件-originExcelFile

2. 得到excel文件的首行各个字段-originString

3. 定义一个数组的列表-basicCheckTable基础字段检查表

4. 建立标准字段与原始字段对应关系表-basicAndHeadStringMap 

  -  流程解读
     -  在basicCheckTable中加入各个数组,例如 String[] idArray = { "id", "ID", " 编号 ", " 教师编号 ", " 教师 ID", " 教师 id" }; 类型.
     -  假设读取到的原始文件的字段为"编号"
     -  从basicCheckTable列表开始外遍历,并逐一遍历basicCheckTable中的各个数组
     -  直到有一个数组中包含了"编号",则我们已经获得了"目标中的标准字段"
     -  在此处即id
     -  PS:此处id对应了数据库中id字段,以及JavaBean中id属性,作为规范,一个标准字段应该书写在string数组第一个
     -  PS:此处设置了"空字段过滤","高级重复字段过滤"以及"错误字段过滤"功能

5.  根据教师 id 获取数据库中对应信息, 并存入 userInfo 对象中 (在项目中,,一定会存在id字段,故以此作为获取数据的标准)

   - 流程解读
     - 使用id查找数据库
     - 获得的数据存入userInfo对象中

6. 使用 userInfo 的动态调用函数方法,根据对应表的key获取数据库中对应的值

   - 流程解读

     - 新建 list 对象, 存储原始文件的头部字段 

       `ArrayList<String> headStringArray = new ArrayList<String>();`

     - 新建 list 对象, 存储每一个字段对应的 value

       `ArrayList<String> headToValueArray = new ArrayList<String>();`

     - 遍历**标准字段与原始字段对应关系表-basicAndHeadStringMap** 

       - 用key(此处即为标准字段,例如id,name等)作为参数,传递到 userInfo 的动态调用函数方法中,得到其对应的值.(例如传参为id,即可获取userInfo中存储的教师编号)
       - 同时将得到的值存入 headToValueArray 中.
       - 为了保持头部字段和从数据库中获取到的数据可以两两对应
         - 在遍历的同时,一步操作中同时写入原始字段(即对应关系表中的value) & 从数据库中得到的值 到headStringArray&headToValueArray中

7. 将headStringArray&headToValueArray写入到excel中

8. **初级的自动数据填充功能完成**