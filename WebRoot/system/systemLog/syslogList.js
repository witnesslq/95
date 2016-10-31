$(function () {
	
	/**
	 加载数据
	*/	
	table= $("#example1").DataTable({	
             "processing": true,//开启读取服务器数据时显示正在加载中……特别是大数据量的时开启此功能比较好
             "serverSide": true,//开启服务器模式，使用服务器端处理配置datatable。
             "processing": true,//是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
//             "sAjaxSource": "sysLogInfoQuery.action", //给服务器发请求的url
             "ajax": {
                 "url":"sysLogInfoQuery.action",
                 "data": function ( d ) {
                     //添加额外的参数传给服务器
                     d.extra_search = $('#reportrange span').html();
                 }},
             "oLanguage": {//插件的汉化
             "sLengthMenu": "每页显示 _MENU_ 条记录",
             "sZeroRecords": "抱歉， 没有找到",
             "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
             "sInfoEmpty": "没有数据",
             "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
             "oPaginate": {
                 "sFirst": "首页",
                 "sPrevious": "前一页",
                 "sNext": "后一页",
                 "sLast": "尾页"
             }, 
		    "sZeroRecords": "没有检索到数据",
            "sProcessing": "处理中...",
            "sSearch": "搜索(用户姓名)",
             },
            "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空
                           {"mData": 'username'}, //listmodel 表示发请求时候本列的列明，返回的数据中相同下标名字的数据会填充到这一列
                           {"mData": function(obj){
                        	   return getdate( obj.logdate);
                               }},
                           {"mData": 'ipaddress'},
                           {"mData": 'action'}
//                           {"sDefaultContent": ''}, // sDefaultContent 如果这一列不需要填充数据用这个属性，值可以不写，起占位作用
//                           {"sDefaultContent": '', "sClass": "action"},//sClass 表示给本列加class
                          ] ,
                          "dom":
                              "<'row'<'col-xs-9'l<'#mytoolbox'>><'col-xs-3'f>r>"+
                              "t"+
                              "<'row'<'col-xs-6'i><'col-xs-6'p>>"  ,
                      initComplete:initComplete             

		 });
		 
		});





/**
 * 表格加载渲染完毕后执行的方法
 * @param data
 */
function initComplete(data){

    var dataPlugin =
            '<div id="reportrange" class="pull-left dateRange" style="width:400px;margin-left: 10px"> '+
            '日期：<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> '+
            '<span id="searchDateRange"></span>  '+
            '<b class="caret"></b></div> ';
    $('#mytoolbox').append(dataPlugin);
    //时间插件
    $('#reportrange span').html(moment().subtract('hours', 1).format('YYYY-MM-DD HH:mm:ss') + ' - ' + moment().format('YYYY-MM-DD HH:mm:ss'));

    $('#reportrange').daterangepicker(
            {
                // startDate: moment().startOf('day'),
                //endDate: moment(),
                //minDate: '01/01/2012',    //最小时间
                maxDate : moment(), //最大时间
                showDropdowns : true,
                showWeekNumbers : false, //是否显示第几周
                timePicker : true, //是否显示小时和分钟
                timePickerIncrement : 60, //时间的增量，单位为分钟
                timePicker12Hour : false, //是否使用12小时制来显示时间
                ranges : {
                    //'最近1小时': [moment().subtract('hours',1), moment()],
                    '今日': [moment().startOf('day'), moment()],
                    '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
                    '最近7日': [moment().subtract('days', 6), moment()],
                    '最近30日': [moment().subtract('days', 29), moment()]
                },
                opens : 'right', //日期选择框的弹出位置
                buttonClasses : [ 'btn btn-default' ],
                applyClass : 'btn-small btn-primary blue',
                cancelClass : 'btn-small',
                format : 'YYYY-MM-DD HH:mm:ss', //控件中from和to 显示的日期格式
                separator : ' to ',
                locale : {
                    applyLabel : '确定',
                    cancelLabel : '取消',
                    fromLabel : '起始时间',
                    toLabel : '结束时间',
                    customRangeLabel : '自定义',
                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                    firstDay : 1
                }
            }, function(start, end, label) {//格式化日期显示框

                $('#reportrange span').html(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
            });

    //设置日期菜单被选项  --结束--


    //选择时间后触发重新加载的方法
    $("#reportrange").on('apply.daterangepicker',function(){
        //当选择时间后，出发dt的重新加载数据的方法
        table.ajax.reload();
        //获取dt请求参数
        var args = table.ajax.params();
        console.log("额外传到后台的参数值extra_search为："+args.extra_search);
    });

  
}