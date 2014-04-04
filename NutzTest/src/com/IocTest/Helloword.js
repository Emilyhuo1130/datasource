/**
 * 
 */
var ioc={
		xiaoming:{
			name:"xiaoming",
			birthday:"2009-10-25 15:23:40"},
		xioahei:{
			type : 'com.pojo.Pet', // 类型
			singleton : false, // 是否为单件
			args : [ 'xioahei' ], // 构造函数参数
			fields : {
				birthday : '2009-11-3 08:02:14',
				friend : {refer : 'xiaoming'}	// 指向容器里另外一个对象
			}
		}
};