/*
 * 				[
					['js','江苏省'],
					['zj','浙江省']
					
				]
				
				
				
 * 
 * */
/**
 * 所有省份*/
var allProvince=[
                 ['bj','北京市'],
                 ['cq','重庆市'],
                 ['sh','上海市'],
                 ['tj','天津市'],
                 ['ah','安徽省'],
                 ['am','澳门特别行政区'],
                 ['fj','福建省'],
                 ['gs','甘肃省'],
                 ['gd','广东省'],
                 ['gx','广西壮族自治区'],
                 ['gz','贵州省'],
                 ['hainan','海南省'],
                 ['heib','河北省'],
                 ['heinan','河南省'],
                 ['hlj','黑龙江省'],
                 ['hub','湖北省'],
                 ['hunan','湖南省'],
                 ['jl','吉林省'],
                 ['js','江苏省'],
                 ['jx','江西省'],
                 ['ln','辽宁省'],
                 ['nmg','内蒙古自治区'],
                 ['nx','宁夏回族自治区'],
                 ['qh','青海省'],
                 ['sd','山东省'],
                 ['s-x','山西省'],
                 ['s---x','陕西省'],
                 ['sc','四川省'],
				 ['xz','西藏自治区 '],
                 ['xj','新疆维吾尔自治区'],
                 ['yn','云南省'],
                 ['zj','浙江省']  
                 ];



/**省包含的城市**/
var bj=[['0','北京市']];
var cq=[['0','重庆市']];
var sh=[['0','上海市']];
var tj=[['0','天津市']];
var ah=[
	['0','安庆市'],
	['1','蚌埠市'],
	['2','亳州市'],
	['3','巢湖市'],
	['4','池州市'],
	['5','滁州市'],
	['6','阜阳市'],
	['7','淮北市'],
	['8','淮南市'],
	['9','黄山市'],
	['10','马鞍山市'],
	['11','宿州市'],
	['12','铜陵市'],
	['13','芜湖市'],
	['14','宣城市']

];
var am=[['0','澳门特别行政区']];
var fj=[
	['0','福州市'],
	['1','龙岩市'],
	['2','南平市'],
	['3','宁德市'],
	['4','莆田市'],
	['5','泉州市'],
	['6','三明市'],
	['7','厦门市'],
	['8','漳州市']
];
var gs=[
	['0','白银市'],
	['1','定西市'],
	['2','甘南藏族自治州'],
	['3','嘉峪关市'],
	['4','金昌市'],
	['5','酒泉市'],
	['6','兰州市'],
	['7','临夏回族自治州'],
	['8','陇南市'],
	['9','平凉市'],
	['10','庆阳市'],
	['11','天水市'],
	['12','武威市'],
	['13','张掖市']
];
var gd=[
	['0','潮州市'],
	['1','东莞市'],
	['2','佛山市'],
	['3','广州市'],
	['4','河源市'],
	['5','惠州市'],
	['6','江门市'],
	['7','揭阳市'],
	['8','茂名市'],
	['9','梅州市'],
	['10','清远市'],
	['11','汕头市'],
	['12','汕尾市'],
	['13','韶关市'],
	['14','深圳市'],
	['15','阳江市'],
	['16','云浮市'],
	['17','湛江市'],
	['18','肇庆市'],
	['19','中山市'],
	['20','珠海市']
];
var gx=[
	['0','百色市'],
	['1','北海市'],
	['2','崇左市'],
	['3','防城港市'],
	['4','贵港市'],
	['5','桂林市'],
	['6','河池市'],
	['7','贺州市'],
	['8','来宾市'],
	['9','柳州市'],
	['10','南宁市'],
	['11','钦州市'],
	['12','梧州市'],
	['13','玉林市']
];
var gz=[
	['0','安顺市'],
	['1','毕节地区'],
	['2','贵阳市'],
	['3','六盘水市'],
	['4','黔东南苗族侗族自治州'],
	['5','黔南布依族苗族自治州'],
	['6','黔西南布依族苗族自治州'],
	['7','铜仁地区'],
	['8','遵义市']
];
var hainan=[
	['0','海口市'],
	['1','三亚市'],
	['2','省直辖县级行政区划']
	
];
var heib=[
	['0','保定市'],
	['1','沧州市'],
	['2','承德市'],
	['3','邯郸市'],
	['4','衡水市'],
	['5','廊坊市'],
	['6','秦皇岛市'],
	['7','石家庄市'],
	['8','唐山市'],
	['9','邢台市'],
	['10','张家口市']
];
var heinan=[
	['0','安阳市'],
	['1','鹤壁市'],
	['2','焦作市'],
	['3','开封市'],
	['4','洛阳市'],
	['5','漯河市'],
	['6','南阳市'],
	['7','平顶山市'],
	['8','濮阳市'],
	['9','三门峡市'],
	['10','商丘市'],
	['11','新乡市'],
	['12','信阳市'],
	['13','许昌市'],
	['14','郑州市'],
	['15','周口市'],
	['16','驻马店市']
];
var hlj=[
	['0','大庆市'],
	['1','大兴安岭地区'],
	['2','哈尔滨市'],
	['3','鹤岗市'],
	['4','黑河市'],
	['5','鸡西市'],
	['6','佳木斯市'],
	['7','牡丹江市'],
	['8','七台河市'],
	['9','齐齐哈尔市'],
	['10','双鸭山市'],
	['11','绥化市'],
	['12','伊春市']
];
var hub=[
	['0','鄂州市'],
	['1','恩施土家族苗族自治州'],
	['2','黄冈市'],
	['3','黄石市'],
	['4','荆门市'],
	['5','荆州市'],
	['6','十堰市'],
	['7','随州市'],
	['8','武汉市'],
	['9','咸宁市'],
	['10','襄樊市'],
	['11','孝感市'],
	['12','宜昌市']
];
var hunan=[
	['0','长沙市'],
	['1','常德市'],
	['2','郴州市'],
	['3','衡阳市'],
	['4','怀化市'],
	['5','娄底市'],
	['6','邵阳市'],
	['7','湘潭市'],
	['8','湘西土家族苗族自治州'],
	['9','益阳市'],
	['10','永州市'],
	['11','岳阳市'],
	['12','张家界市'],
	['13','株洲市']
];
var jl=[
	['0','白城市'],
	['1','白山市'],
	['2','长春市'],
	['3','吉林市'],
	['4','辽源市'],
	['5','四平市'],
	['6','松原市'],
	['7','通化市'],
	['8','延边朝鲜族自治州']
];
var js=[
	['0','常州市'],
	['1','淮安市'],
	['2','连云港市'],
	['3','南京市'],
	['4','南通市'],
	['5','苏州市'],
	['6','宿迁市'],
	['7','泰州市'],
	['8','无锡市'],
	['9','徐州市'],
	['10','盐城市'],
	['11','扬州市'],
	['12','镇江市']
];
var jx=[
	['0','抚州市'],
	['1','赣州市'],
	['2','吉安市'],
	['3','景德镇市'],
	['4','九江市'],
	['5','南昌市'],
	['6','萍乡市'],
	['7','上饶市'],
	['8','新余市'],
	['9','宜春市'],
	['10','鹰潭市']
];

var ln=[
	['0','鞍山市'],
	['1','本溪市'],
	['2','朝阳市'],
	['3','大连市'],
	['4','丹东市'],
	['5','抚顺市'],
	['6','阜新市'],
	['7','葫芦岛市'],
	['8','锦州市'],
	['9','辽阳市'],
	['10','盘锦市'],
	['11','沈阳市'],
	['12','铁岭市'],
	['13','营口市']
];

var nmg=[
	['0','阿拉善盟'],
	['1','赤峰市'],
	['2','鄂尔多斯市'],
	['3','呼和浩特市'],
	['4','呼伦贝尔市'],
	['5','通辽市'],
	['6','乌海市'],
	['7','乌兰察布市'],
	['8','锡林郭勒盟'],
	['9','兴安盟']
];
var nx=[
	['0','固原市'],
	['1','石嘴山市'],
	['2','吴忠市'],
	['3','银川市'],
	['4','中卫市']
];
var qh=[
	['0','果洛藏族自治州'],
	['1','海北藏族自治州'],
	['2','海东地区'],
	['3','海南藏族自治州'],
	['4','海西蒙古族藏族自治州'],
	['5','黄南藏族自治州'],
	['6','西宁市'],
	['7','玉树藏族自治州']
];
var sd=[
	['0','滨州市'],
	['1','德州市'],
	['2','东营市'],
	['3','菏泽市'],
	['4','济南市'],
	['5','济宁市'],
	['6','莱芜市'],
	['7','聊城市'],
	['8','临沂市'],
	['9','青岛市'],
	['10','日照市'],
	['11','泰安市'],
	['12','威海市'],
	['13','潍坊市'],
	['14','烟台市'],
	['14','枣庄市'],
	['14','淄博市']
];
var s_x=[
	['0','长治市'],
	['1','大同市'],
	['2','晋城市'],
	['3','晋中市'],
	['4','临汾市'],
	['5','吕梁市'],
	['6','朔州市'],
	['7','太原市'],
	['8','忻州市'],
	['9','阳泉市'],
	['10','运城市']
];
var s___x=[
	['0','安康市'],
	['1','宝鸡市'],
	['2','汉中市'],
	['3','商洛市'],
	['4','铜川市'],
	['5','渭南市'],
	['6','西安市'],
	['7','咸阳市'],
	['8','延安市'],
	['9','榆林市']
];
var sc=[
	['0','阿坝藏族羌族自治州'],
	['1','巴中市'],
	['2','成都市'],
	['3','达州市'],
	['4','德阳市'],
	['5','甘孜藏族自治州'],
	['6','广安市'],
	['7','广元市'],
	['8','乐山市'],
	['9','凉山彝族自治州'],
	['10','泸州市'],
	['11','眉山市'],
	['12','绵阳市'],
	['13','内江市'],
	['14','南充市'],
	['10','攀枝花市'],
	['11','遂宁市'],
	['12','雅安市'],
	['13','宜宾市'],
	['14','资阳市'],
	['14','自贡市']
];
var xz=[
	['0','阿里地区'],
	['1','昌都地区'],
	['2','拉萨市'],
	['3','林芝地区'],
	['4','那曲地区'],
	['5','日喀则地区'],
	['6','山南地区']
];
var xj=[
	['0','阿克苏地区'],
	['1','阿勒泰地区'],
	['2','巴音郭楞蒙古自治州'],
	['3','博尔塔拉蒙古自治州'],
	['4','昌吉回族自治州'],
	['5','哈密地区'],
	['6','和田地区'],
	['7','喀什地区'],
	['8','克拉玛依市'],
	['9','克孜勒苏柯尔克孜自治州'],
	['10','塔城地区'],
	['11','吐鲁番地区'],
	['12','乌鲁木齐市'],
	['13','伊犁哈萨克自治州'],
	['14','自治区直辖县级行政区划']
];
var yn=[
	['0','保山市'],
	['1','楚雄彝族自治州'],
	['2','大理白族自治州'],
	['3','德宏傣族景颇族自治州'],
	['4','迪庆藏族自治州'],
	['5','红河哈尼族彝族自治州'],
	['6','昆明市'],
	['7','丽江市'],
	['8','临沧市'],
	['9','怒江僳僳族自治州'],
	['10','普洱市'],
	['11','曲靖市'],
	['12','文山壮族苗族自治州'],
	['13','西双版纳傣族自治州'],
	['14','玉溪市'],
	['14','昭通市']
];
var zj=[
	['0','杭州市'],
	['1','湖州市'],
	['2','嘉兴市'],
	['3','金华市'],
	['4','丽水市'],
	['5','宁波市'],
	['6','衢州市'],
	['7','绍兴市'],
	['8','台州市'],
	['9','温州市'],
	['10','舟山市']
	]

/**省包含的城市的引用*/
var province_citys={
		'bj':bj,
		'cq':cq,
		'sh':sh,
		'tj':tj,
		'ah':ah,
		'am':am,
		'fj':fj,
		'gs':gs,
		'gd':gd,
		'gx':gx,
		'gz':gz,
		'hainan':hainan,
		'heib':heib,
		'heinan':heinan,
		'hlj':hlj,
		'hub':hub,
		'hunan':hunan,
		'jl':jl,
		'js':js,
		'jx':jx,
		'ln':ln,
		'nmg':nmg,
		'nx':nx,
		'qh':qh,
		'sd':sd,
		's_x':s_x,
		's___x':s___x,
		'sc':sc,
		'xz':xz,
		'xj':xj,
		'yn':yn,
		'zj':zj,
		'test':'test'
	};










