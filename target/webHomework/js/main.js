$(function () {
    $('.a-user').on('click', function () {
        $('.user').show();
        $('.other').hide();
        $('.sale').hide();
        $('.storage').hide();
        $('.goods-rule').hide();
    });

    $('.a-other').on('click', function () {
        $('.user').hide();
        $('.other').show();
        $('.sale').hide();
        $('.storage').hide();
        $('.goods-rule').hide();
    });

    $('.icon-a-xiala2').on('click', function () {
        if ($('.a-container').css('display') == 'none') {
            $('.a-container').css('display', 'block');
        } else {
            $('.a-container').css('display', 'none');
        }
    });

    $('.b').on('click', function () {
        $('.user').hide();
        $('.other').hide();
        $('.sale').show();
        $('.storage').hide();
        $('.goods-rule').hide();
    });

    $('.d').on('click', function () {
        $('.user').hide();
        $('.other').hide();
        $('.sale').hide();
        $('.storage').hide();
        $('.goods-rule').show();
    });

    $('.home').on('click', function () {
        $('.user').hide();
        $('.other').hide();
        $('.sale').hide();
        $('.storage').hide();
        $('.goods-rule').hide();
    });

    /**
     * 功能：通过传入的用户名传入后端在db中查询，并将查询的数据显示在前端页面当中
     * @param currentUserName   要查询的用户名
     */
    function getUserInfo(currentUserName) {
        const userInfoPage = $('.popupWindow');

        $.ajax({
            url: 'getUserInfo',
            method: 'GET',
            data: { currentUserName: currentUserName },

            success: function (response) {
                console.log(response);
                //将get方法获取的数据添加的前端的页面当中
                $('.popupWindow .userInfoBox .userInfoUN input').val(response.userName);
                $('.popupWindow .userInfoBox .userInfoRealNa input').val(response.name);
                $('.popupWindow .userInfoBox .userInfoSex input').val(response.gender);
                $('.popupWindow .userInfoBox .userInfoPhone input').val(
                    response.telephone
                );
            },
            error: function (error) {
                console.log(error);
            }
        });

        userInfoPage.removeClass('popWinHidden');
        userInfoPage.show();
        $('.popupWindow .userInfoBox').show();
        $('.popupWindow .userInfoBox .confirmMod').hide();
        $('.popupWindow .receiveMessBox').hide();
        $('.popupWindow .sendMessageBox').hide();
    }

    $('.userInfo').on('click', function () {
        var currentUserName = document.getElementById('currentUsN').textContent;
        const userInfoPage = $('.popupWindow');
        getUserInfo(currentUserName);

        //运用setTimeout延迟函数使得页面过渡得以实现
        setTimeout(function () {
            //将opacity设为使得页面得以显现
            userInfoPage.css('opacity', '1');
        }, 10);
    });

    $('.popupWindow .userInfoBox .closeButton').on('click', function () {
        $('.popupWindow').addClass('popWinHidden');
        //将opacity设为使得页面变得不可见
        $('.popupWindow').css('opacity', '0');

        //运用setTimeout延迟函数使得页面过渡得以实现
        setTimeout(function () {
            //将前端的页面的数据全部清除
            $('.popupWindow .userInfoBox .userInfoUN input').val('');
            $('.popupWindow .userInfoBox .userInfoRealNa input').val('');
            $('.popupWindow .userInfoBox .userInfoSex input').val('');
            $('.popupWindow .userInfoBox .userInfoPhone input').val('');
            $('.popupWindow .userInfoBox .oldPW input').val('');
            $('.popupWindow .userInfoBox .newPW input').val('');
            $('.popupWindow .userInfoBox .reNewPW input').val('');
            //将前端的input全部变为不可修改
            $('.popupWindow .userInfoBox .userInfoRealNa input').prop(
                'disabled',
                true
            );
            $('.popupWindow .userInfoBox .userInfoSex input').prop('disabled', true);
            $('.popupWindow .userInfoBox .userInfoPhone input').prop(
                'disabled',
                true
            );
            $('.popBox .userInfoBox .pswtable').hide();
            $('.popupWindow').hide();
        }, 300);

        $('.userInfo').removeClass('act');
        $('.home').addClass('act');
    });

    $('.sendMessage').on('click', function () {
        $.ajax({
            url: 'getAllUserName',
            method: 'GET',

            success: function (response) {
                console.log(response);
                // 后端返回的数据
                var data = response;
                var re = document.getElementById('recipient');
                //将后端获取的相关数据添加到select标签当中
                data.forEach(function (i) {
                    var option = document.createElement('option');
                    option.textContent = i;
                    option.value = i;
                    re.append(option);
                });
            },
            error: function (error) {
                console.log(error);
            }
        });
        const userInfoPage = $('.popupWindow');
        userInfoPage.removeClass('popWinHidden');
        userInfoPage.show();
        $('.popupWindow .userInfoBox').hide();
        $('.popupWindow .receiveMessBox').hide();
        $('.popupWindow .sendMessageBox').show();
        setTimeout(function () {
            userInfoPage.css('opacity', '1');
        }, 10);
    });

    $('.popupWindow .sendMessageBox .closeButton').on('click', function () {
        $('#recipient').empty();

        $('.popupWindow').addClass('popWinHidden');
        $('.popupWindow').css('opacity', '0');

        setTimeout(function () {
            $('.popupWindow').hide();
        }, 300);

        $('.sendMessage').removeClass('act');
        $('.home').addClass('act');
    });

    $('.receiveMessage').on('click', function () {
        var currentUserName = document.getElementById('currentUsN').textContent;

        $.ajax({
            url: 'receiveMessage',
            method: 'GET',
            data: { currentUserName: currentUserName },

            success: function (response) {
                console.log(response);
                // 后端返回的数据
                var data = response;
                //创建一个数组来添加receivedMessageList的div标签
                var rows = [];
                var index = 1;
                response.forEach(function (i) {
                    // 提取月份和日期
                    var dateParts = i.sendTime.split('-');
                    var month = dateParts[1];
                    var day = dateParts[2].split(' ')[0];
                    // 输出结果
                    var calendar = month + '-' + day;
                    var div = document.createElement('div');
                    div.classList.add('receivedMessageList');

                    //将相关数据添加的数组当中
                    rows.push(
                        `
                                        <div class="receivedMessageList" ` +
                        `id=message` +
                        index +
                        `>` +
                        `<div class="messInfoList">发送人：<span class="sender">` +
                        i.sender +
                        `</span>  发送时间:<span class="sendTime">` +
                        i.sendTime +
                        `</span></div>
                                        <i class="iconfont icon-rili"><span>` +
                        calendar +
                        `</span></i>
                                        <i class="iconfont icon-shanchu"></i>
                                        <div class="messContent">` +
                        i.message +
                        `</div></div>
                                        `
                    );
                    index++;
                });
                $('#messageContainer').append(rows.join(''));
            },

            error: function (error) {
                console.log(error);
            }
        });

        const userInfoPage = $('.popupWindow');
        userInfoPage.removeClass('popWinHidden');
        $('.popupWindow .receiveMessBox').show();
        userInfoPage.show();
        $('.popupWindow .userInfoBox').hide();
        $('.popupWindow .sendMessageBox').hide();
        setTimeout(function () {
            userInfoPage.css('opacity', '1');
        }, 10);
    });

    $('#messageContainer').on('click', '.iconfont.icon-shanchu', function () {
        var result = confirm('是否删除？');
        if (result) {
            // 执行删除操作的代码
            var index = $(this).parent().attr('id');
            var sendTime = $(this).parent().find('.sendTime').text();
            var currentUserName = document.getElementById('currentUsN').textContent;
            $.ajax({
                url: 'deleteMessage',
                type: 'POST',
                data: { sendTime: sendTime, currentUserName: currentUserName },
                success: function (response) {
                    console.log(response);
                    $('#' + index).remove();
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        } else {
        }
    });

    $('.popupWindow .receiveMessBox .closeButton').on('click', function () {
        $('.popupWindow').addClass('popWinHidden');
        $('.popupWindow').css('opacity', '0');

        setTimeout(function () {
            $('.popupWindow').hide();
            $('.receivedMessageList').empty().remove();
        }, 300);

        $('.receiveMessage').removeClass('act');
        $('.home').addClass('act');
    });

    $('#submitButton').click(function () {
        var recipient = document.getElementById('recipient').value;
        var message = document.getElementById('message').value;
        var sender = document.getElementById('currentUsN').textContent;
        var sendtime = '1';
        var requestData = {
            sender: sender,
            recipient: recipient,
            message: message,
            sendTime: sendtime
        };
        $.ajax({
            type: 'POST',
            url: 'sendMessage',
            //将数据以json格式打包
            data: JSON.stringify(requestData),
            contentType: 'application/json',
            success: function (response) {
                alert('发送成功');
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });

    $('.editpsw').on('click', function () {
        $('.popBox .userInfoBox .pswtable').show();
        $('.userInfoBox .pswtable tr:nth-child(2) input').focus();
    });

    $('.userInfoBox .edit').on('click', function () {
        $('.popupWindow .userInfoBox  .confirmMod').show();
        $('.popupWindow .userInfoBox .userInfoRealNa input').prop(
            'disabled',
            false
        );
        $('.popupWindow .userInfoBox .userInfoSex input').prop('disabled', false);
        $('.popupWindow .userInfoBox .userInfoPhone input').prop('disabled', false);
    });

    $('.other .other-data table .loginTr .deleteButton').on('click', function () {
        var result = confirm('是否删除？');
        if (result) {
            // 执行删除操作的代码
            var logintime = $(this).parent().parent().find('.loginTime').text();
            $.ajax({
                type: 'POST',
                url: 'deleteLoginRecord',
                data: { loginTime: logintime },
                success: function (response) {
                    console.log(response);
                    $(this).parent().parent().remove();
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
            $(this).parent().parent().remove();
        } else {
        }
    });

    $('.user .user-data table .userInfoTr .deleteButton').on(
        'click',
        function () {
            var result = confirm('是否删除？');
            if (result) {
                // 执行删除操作的代码
                var userName = $(this).parent().parent().find('.userInfoUsN').text();
                $.ajax({
                    type: 'POST',
                    url: 'deleteUserInfo',
                    data: { userName: userName },
                    success: function (response) {
                        console.log(response);
                        $(this).parent().parent().remove();
                    },
                    error: function (xhr, status, error) {
                        console.error(error);
                    }
                });
                $(this).parent().parent().remove();
            } else {
            }
        }
    );

    $('.user .user-data table .userInfoTr  .modifyButton').on(
        'click',
        function () {
            var userName = $(this).parent().parent().find('.userInfoUsN').text();
            const userInfoPage = $('.popupWindow');
            getUserInfo(userName);

            setTimeout(function () {
                userInfoPage.css('opacity', '1');
            }, 10);
        }
    );

    $('.popBox .userInfoBox .confirmMod').on('click', function () {
        var userName = $('.popupWindow .userInfoBox .userInfoUN input').val();
        var realNa = $('.popupWindow .userInfoBox .userInfoRealNa input').val();
        var sex = $('.popupWindow .userInfoBox .userInfoSex input').val();
        var phone = $('.popupWindow .userInfoBox .userInfoPhone input').val();

        $.ajax({
            type: 'POST',
            url: 'modifyUserInfo',
            data: {
                userName: userName,
                realNa: realNa,
                sex: sex,
                phone: phone
            },

            success: function (response) {
                console.log(response);
                alert(response);
            },

            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });

    $('.popBox .userInfoBox .submit').on('click', function () {
        var oldPW = $('.popupWindow .userInfoBox .oldPW input').val();
        var newPW = $('.popupWindow .userInfoBox .newPW input').val();
        var reNewPW = $('.popupWindow .userInfoBox .reNewPW input').val();
        var userName = $('.popupWindow .userInfoBox .userInfoUN input').val();

        var reg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{8,16}$/;
        var cheakNewPW = reg.test(newPW);
        if (cheakNewPW != true) {
            alert(
                '新密码要求至少包含大/小写字母或特殊字符中的两种而且长度在8-16位之间'
            );
        } else if (newPW === reNewPW) {
            $.ajax({
                type: 'POST',
                url: 'modifyPassword',
                data: {
                    oldPW: oldPW,
                    newPW: newPW,
                    userName: userName
                },
                success: function (response) {
                    console.log(response);
                    alert(response);
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        } else {
            alert('输入的新密码不一致');
        }
    });

    $('.user .up .s2 .btn1').on('click', function () {
        var searchType = $('#userSearchType').val();
        var searchInput = $('.user .searchInput').val();
        var userList = $('.userInfoTr .' + searchType);
        var searchResult = search(userList, searchInput);
        //将所有表格隐藏
        var tr = $('.userInfoTr');
        tr.hide();
        //将搜索到的表格行显示出来
        for (let i = 0; i < searchResult.length; i++) {
            var index = searchResult[i];
            tr.eq(index).show();
        }
    });

    $('.sale .s2  .btn1').on('click', function () {
        var searchType = $('#saleSelect').val();
        var searchInput = $('.sale .searchInput').val();
        var userList = $('.saleTr .' + searchType);
        var searchResult = search(userList, searchInput);
        //将所有表格隐藏
        var tr = $('.saleTr');
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

    $('.goods-rule .other-data table  .deleteButton').on('click', function () {
        var result = confirm('是否删除？');
        if (result) {
            // 执行删除操作的代码
            var changeTime = $(this).parent().parent().find('.changeTime').text();
            $.ajax({
                type: 'POST',
                url: 'deleteGoodRecord',
                data: { changeTime: changeTime },
                success: function (response) {
                    console.log(response);
                    $(this).parent().parent().remove();
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
            $(this).parent().parent().remove();
        } else {
        }
    });

    $(".pay").on("click",function(){
        console.log('list', document.querySelectorAll('.saleTr'))
        var goodTr = document.querySelectorAll('.saleTr')
        let domTotal = []
        let goodInfo = []
        let domList = document.querySelectorAll('.number')
        domList.forEach(v => {
            domTotal.push(v.defaultValue)
        })
        goodTr.forEach(v => {
            var innerText1 = v.innerText.split("\t").slice(0, -1)
            goodInfo.push(innerText1)
        })

        let allMessage = goodInfo.map((v, index) => [...v, domTotal[index]] )
        let totalPrice = document.querySelectorAll('.sum-price')[0].innerHTML

        allMessage = JSON.stringify(allMessage)
        console.log(JSON.stringify(allMessage))
        console.log(allMessage.toString())
        $.ajax({
            url: "payGoods",
            method: 'POST',
            data: { "allMessage": allMessage, "totalPrice": totalPrice},
            success: function(response){
                console.log(response, 'response...')
                let domList = document.querySelectorAll('.number')
                domList.forEach(v => {
                    // console.log(v, 'v......')
                    v.setAttribute('value', 0)
                    document.querySelectorAll('.sum-price')[0].innerHTML = 0
                })
                alert("结算成功");
            },
            error: function(error){
                console.log(error);
            }
        });
    })
    //////////////////在这之后加代码///////////////////

    ////////////////////////////////////////////////
});
