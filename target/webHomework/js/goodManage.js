$(function () {
    $(".kucunShujv").on("click",function () {
        fetch('getGoodAmount') // 发送GET请求
            .then(response => response.json()) // 解析响应为JSON格式
            .then(data => {
                const goodNames = data.map(item => item.goodName).reverse();
                const goodNums = data.map(item => item.goodNum).reverse();
                option = {
                    color: [
                        '#6f01fb',
                    ],
                    title:{
                      text:"货物数量top10"
                    },
                    yAxis: {
                        type: 'category',
                        data: goodNames,
                        axisLabel:{
                            formatter:function (value) {
                                if (value.length > 3)
                                    return (value.substring(0,  3) + "...");
                                else
                                    return value;
                            }
                        }
                    },
                    xAxis: {
                        type: 'value'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    series: [
                        {
                            data: goodNums,
                            type: 'bar',
                        }
                    ]
                };
                const chart = echarts.init(document.getElementById('goodAmount')); // chart-container为指定的HTML元素ID
                chart.setOption(option);
                chart.resize();
            })
            .catch(error => {
                // 处理错误
                console.error(error);
            });

    })
    $(".pr").on("click",function () {
        fetch('getProfit') // 发送GET请求
            .then(response => response.json()) // 解析响应为JSON格式
            .then(data => {
                console.log(data)
                const sellTimes = data.map(item => item.sellTime);
                const profits = data.map(item => item.profit)
                var arr = data.map(
                    item =>{
                        return {
                            value: item.profit,
                            name:item.sellTime
                        }
                    }
                );
                console.log(arr);
                option = {
                    tooltip: {
                        trigger: 'item'
                    },
                    title:{
                        text:"利润最高天数Top5"
                    },
                    legend: {
                        top: '5%',
                        left: 'center'
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: ['40%', '70%'],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 10,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            data: arr
                        }
                    ]
                };
                const chart = echarts.init(document.getElementById('profitAmount')); // chart-container为指定的HTML元素ID
                chart.setOption(option);
                chart.resize();
            });
    })

    $(".user").on("click",function () {
        fetch('getUserLoginAmount') // 发送GET请求
            .then(response => response.json()) // 解析响应为JSON格式
            .then(data => {
                const arr = data.map(
                    item =>{
                        return {
                            name: item.userName,
                            value:item.loginNum
                        }
                    }
                );
                console.log(arr)
                option = {
                    color:[
                        '#ffc0cb',
                        '#ffa500',
                        '#7fffd4',
                        '#ff6347',
                        '#808080',
                        '#516b91',
                        '#59c4e6',
                        '#edafda',
                        '#93b7e3',
                        '#a5e7f0',
                        '#cbb0e3',
                    ],
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    title:{
                      text:'所用用户的登录次数',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: { show: true },
                            dataView: { show: true, readOnly: false },
                            restore: { show: true },
                            saveAsImage: { show: true }
                        }
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: ['15%', '80%'],
                            roseType: 'area',
                            itemStyle: {
                                borderRadius: 8
                            },
                            data: arr
                        }
                    ]
                };
                const chart = echarts.init(document.getElementById('userLoginAmout')); // chart-container为指定的HTML元素ID
                chart.setOption(option);
                window.addEventListener('resize', function() {
                    chart.resize();
                });
                chart.resize();
            })

        fetch('getLoginTimeAmount') // 发送GET请求
            .then(response => response.json()) // 解析响应为JSON格式
            .then(data => {
                    const times = data.map(item=>item.time);
                    const counts = data.map(item=>item.count);
                option = {
                    tooltip: {
                        trigger: 'axis',
                    },
                    title : {
                        text : '每日登录次数',
                        left : 'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: times,
                        axisLabel:{
                            formatter:function (value) {
                               var a = value.split("-")
                                return (a[0] + "-" + '\n' + a[1] + "-" + '\n' + a[2]);
                            }
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: counts,
                            type: 'line'
                        }
                    ]
                };
                const chart = echarts.init(document.getElementById('getLoginTimeAmount')); // chart-container为指定的HTML元素ID
                chart.setOption(option);
                window.addEventListener('resize', function() {
                    chart.resize();
                });
                chart.resize();
            })
    })

    $(".ip").on("click",function () {


        fetch('getLoginAddress') .then(response => response.json()) // 解析响应为JSON格式
            .then(data => {
                const arr = data.map(
                    item =>{
                        return {
                            value: item.count,
                            name: item.address
                        }
                    }
                );

           console.log(arr);
            option = {
                color: ['#5470c6',
                    '#91cc75' ,
                    '#fac858' ,
                    '#ee6666' ,
                    '#73c0de' ,
                    '#3ba272' ,
                    '#fc8452' ,
                    '#9a60b4' ,
                    '#ea7ccc'],
                tooltip: {
                    trigger: 'item'
                },
                title :{
                    text:"登录ip的归属地",
                    left:'center',
                    top:'10'
                },
                series: [
                    {
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 20,
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: arr
                    }
                ]
            };
            const chart = echarts.init(document.getElementById('Ipaddress')); // chart-container为指定的HTML元素ID
            chart.setOption(option);
            window.addEventListener('resize', function() {
                chart.resize();
            });
            chart.resize();
        })

        fetch('getIp').then(response => response.json())
            .then(data=>{
                const arr = data.map((item)=>{
                    return{
                        value:item.count,
                        name:item.loginIp
                    }
                })
                option = {

                    title: {
                        text: '登陆时的ip分布',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        top: '5%',
                        left: 'center'
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '50%',
                            data: arr,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                const chart = echarts.init(document.getElementById('loginIp')); // chart-container为指定的HTML元素ID
                chart.setOption(option);
                window.addEventListener('resize', function() {
                    chart.resize();
                });
                chart.resize();
            })

    })

   $(".goodsinfotable .modifyButton").on("click",function () {
       $(this).parent().find('.sure').show();
       $(this).parent().parent().find('input').prop('disabled', false);
   })

    $(".goodsinfotable .sure").on("click",function () {
        var id = $(this).parent().parent().find('.id').text();
        var goodName = $(this).parent().parent().find('.goodName').val();
        console.log(goodName);
        var manufacturer = $(this).parent().parent().find('.manufacturer').val();
        var buyPrice = $(this).parent().parent().find('.buyPrice').val();
        var sellPrice = $(this).parent().parent().find('.sellPrice').val();
        var goodNum = $(this).parent().parent().find('.goodNum').val();
        console.log(id);
        var result = confirm('是否修改？');
        if (result) {
            $.ajax({
                type : 'POST',
                url : 'modifyGoodInfo',
                data : {
                    id : id,
                    goodName : goodNaSSme,
                    manufacturer : manufacturer,
                    buyPrice : buyPrice,
                    sellPrice : sellPrice,
                    goodNum : goodNum
                },
                success : function (response) {
                    console.log(response);
                    alert(response);
                },

                error: function (xhr, status, error) {
                    alert("输入的数据有错误");
                    console.error(error);
                }
            })
        }else {

        }

        $(this).parent().find('.sure').hide();
        $(this).parent().parent().find('input').prop('disabled', true);
    })

    $(".goodsinfotable .deleteButton").on("click",function () {
        var id = $(this).parent().parent().find('.id').text();
        var result = confirm('是否删除？');
        if (result) {
            $.ajax({
                type: 'POST',
                url: "deleteGoodInfo",
                data: {id: id},
                success : function (response) {
                    console.log(response);
                    $(this).parent().parent().remove();
                },

                error: function (xhr, status, error) {
                    console.error(error);
                }
            })
            $(this).parent().parent().remove();
        }else {

        }

    });

    $('.modifykucun .s2  .btn1').on('click', function () {
        var searchType = $('#saleSelect').val();
        var searchInput = $('.modifykucun .searchInput').val();
        console.log(searchInput)
        console.log(searchType)
        var userList = $('.goodTr .' + searchType );
        var searchResult = search(userList, searchInput);
        //将所有表格隐藏
        var tr = $('.goodTr');
        tr.hide();
        //将搜索到的表格行显示出来
        for (let i = 0; i < searchResult.length; i++) {
            var index = searchResult[i];
            tr.eq(index).show();
        }
    });

    function search(searchArr, searchString) {
        var searchResult = [];
        for (let i = 0; i < searchArr.length; i++) {
            if (searchArr[i].textContent.includes(searchString)) {
                searchResult.push(i);
            }
        }
        return searchResult;
    }

    $('.modifykucun .s2  .btn2').on('click', function () {
        var tr = $('.goodTr');
        tr.hide();

    })

    $(".saleExplore").on('click',function () {
        fetch('getGoodSale').then(response => response.json()).then(data => {
            const arr = data.map((item)=>{
                return{
                    value:item.sale,
                    name:item.goodName
                }
            })
            option = {
                color: [ '#3ba272' ,
                    '#fc8452' ,
                    '#9a60b4' ,
                    '#ea7ccc',
                    '#5470c6',
                    '#91cc75' ,
                    '#fac858' ,
                    '#ee6666' ,
                    '#73c0de' ,
                   ],
                title: {
                    text: '货物销售数量',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        type: 'pie',
                        radius: '50%',
                        data: arr,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            const chart = echarts.init(document.getElementById('goodSale'));
            chart.setOption(option);
            window.addEventListener('resize', function() {
                chart.resize();
            });
            chart.resize();
        })

        fetch('getDateSale').then(response => response.json()).then(data => {
            const dates = data.map(item=>item.data);
            console.log(dates)
            const counts = data.map(item=>item.count);
            option = {
                color : ['#91cc75'],
                title:{
                    text : '每天销售情况',
                    left:'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: dates,
                    axisLabel:{
                        formatter:function (value) {
                            var a = value.split("-")
                            return (a[0] + "-" + '\n' + a[1] + "-" + '\n' + a[2]);
                        }
                    }
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: counts,
                        type: 'line',
                        areaStyle: {}
                    }
                ]
            };
            const chart = echarts.init(document.getElementById('dateSale'));
            chart.setOption(option);
            window.addEventListener('resize', function() {
                chart.resize();
            });
            chart.resize();

        })

    })
    
       $('.kucunShujv .btn1').on('click',function () {
           var select = document.getElementById("goodSelect");
           var selectedValue = select.value;
           // 使用选中的值进行后续操作
           console.log(selectedValue);

           $.ajax({
               type:'GET',
               url: "getGoodDaySale",
               data:{
                   goodName:selectedValue
               },
               success : function (response) {
                   console.log(response);
                   const times = response.map(item=>item.time);
                   const counts = response.map(item=>item.count);

                   option = {
                       title:{
                           text:'货物销售记录',
                           left:'center',
                       },
                       tooltip: {
                           trigger: 'axis'
                       },
                       xAxis: {
                           type: 'category',
                           data: times,
                           axisLabel:{
                               formatter:function (value) {
                                   var a = value.split("-")
                                   return (a[0] + "-" + '\n' + a[1] + "-" + '\n' + a[2]);
                               }
                            }
                       },
                       yAxis: {
                           type: 'value'
                       },
                       series: [
                           {
                               data: counts,
                               type: 'line',
                               smooth: true
                           }
                       ]
                   };

                   const chart = echarts.init(document.getElementById('goodSaleDay'));
                   chart.setOption(option);
                   window.addEventListener('resize', function() {
                       chart.resize();
                   });
                   chart.resize();
               },

               error: function (xhr, status, error) {
                   alert("输入的数据有错误");
                   console.error(error);
               }
           })
       })

    $("#modifykucun .btn2").on("click",function (){
        $(".goodsinfo").hide();
        $(".edit-sale").show();
    });

    $(".edit-sale .ok").on("click",function (){
        $(".goodsinfo").show();
        $(".edit-sale").hide();
        var id = $(".edit-sale .id").val();
        var goodName = $(".edit-sale .goodName").val();
        var manufacturer = $(".edit-sale .manufacturer").val();
        var buyPrice = $(".edit-sale .buyPrice").val();
        var sellPrice = $(".edit-sale .sellPrice").val();
        var goodNum = $(".edit-sale .goodNum").val();
        var result = confirm('是否添加？');
        if (result) {
            $.ajax({
                type: "POST",
                url: "insertGoodInfo",
                data: {
                    id: id,
                    goodName: goodName,
                    manufacturer: manufacturer,
                    buyPrice: buyPrice,
                    sellPrice: sellPrice,
                    goodNum: goodNum
                },
                success : function (response) {
                    console.log(response);
                    alert(response);
                },

                error: function (xhr, status, error) {
                    alert("输入的数据有错误");
                    console.error(error);
                }
            })
        }else {

        }
    });


});