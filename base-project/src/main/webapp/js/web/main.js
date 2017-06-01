/**
 * Created by Admin on 2016/12/23.
 */
$(function () {

    var ScadaTab = {
        template: '#scadaTab',
        data: function () {
           return{
           }
        },
        mounted: function () {
            $('.main .main-left li').removeClass('active');
            $('.main .main-left li').eq(0).addClass('active');
            this.$nextTick(function () {
                var msgflag = 'add';
                var editorIndex = -1;
                var section = '';

                // 创建section元素
                function setSection(dimension, min, max, count) {
                    section = $('<section>' +
                        '<div class="editorBox">' +
                        '<button class="sectionEditor">Editor</button>' +
                        '<button class="sectionDel">Del</button>' +
                        '</div>' +
                        '<h3>' +
                        '<span class="dimensionTitle">' + dimension + '</span>' +
                        '<span class="info">min:</span>' +
                        '<span class="dimensionMin">' + min + '</span>' +
                        '<span class="info">max:</span>' +
                        '<span class="dimensionMax">' + max + '</span>' +
                        '<span class="info">count:</span>' +
                        '<span class="dimensionCount">' + count + '</span>' +
                        '</h3>' +
                        '</section>');
                    if ( min == "" || max == "") {
                        section.find('.dimensionMin').prev().hide();
                        section.find('.dimensionMin').hide();
                        section.find('.dimensionMax').prev().hide();
                        section.find('.dimensionMax').hide();
                    }else{
                        section.find('.dimensionMin').prev().show();
                        section.find('.dimensionMin').show();
                        section.find('.dimensionMax').prev().show();
                        section.find('.dimensionMax').show();
                    }
                }

                // 创建在ul元素中append元素
                function setUl(index, params, min, max, ul) {
                    ul.append($('<li>' +
                        '<div class="liChild">' +
                        '<div class="tr">' +
                        '<span>指标'+ index +'</span>' +
                        '<span>最小值</span>' +
                        '<span>最大值</span>' +
                        '</div>' +
                        '<div class="tr">' +
                        '<span class="params">'+ params +'</span>' +
                        '<span class="min">'+ min +'</span>' +
                        '<span class="max">'+ max +'</span>' +
                        '</div>' +
                        '</div>' +
                        '</li>')
                    );
                }

                // 初始化编辑信息面板
                function initMsg() {
                    $('#dimension').val('');
                    $('#min').val('');
                    $('#max').val('');
                    $('#count').val('');
                    $('#max').prop('disabled', false);
                    $('#min').prop('disabled', false);
                    for (var j = $('.msgPlane .innerPlane table tr').length; j > 0;j--) {
                        $($('.msgPlane .innerPlane table tr')[j]).remove();
                    }
                }

                // 初始化获取app列表
                $.ajax({
                    url: 'api/plug/getPlugList.do',
                    method: 'POST',
                    success: function (data) {
                        setPlugList(data.data);
                    }
                });

                // 创建app的option列表
                function setPlugList (arr) {
                    for (var i = 0; i < arr.length; i++) {
                        var option = $("<option value='" + arr[i].id + "'>" + arr[i].plugName + "</option>");
                        $('.selectapp select').append(option);
                    }
                }

                $('.selectapp select').off('change');
                // 当selectapp发生变化的时候
                $('.selectapp select').on('change', function () {
                    setPointAjax($(this));
                });
                function setPointAjax(selectapp) {
                    var plugId = selectapp.val();
                    $.ajax({
                        url: 'api/dimension/getDimensionListSel.do',
                        method: 'POST',
                        data: {
                            plugId: plugId
                        },
                        success: function (data) {
                            setPointList(data.data);
                        }
                    });
                }
                // 根据app列表获取打点列表
                function setPointList (arr) {
                    for (var i = $('.selectpoint select option').length; i > 0 ;i--) {
                        var optionIndex = $('.selectpoint select option')[i];
                        $(optionIndex).remove();
                    }
                    for (var i = 0; i < arr.length; i++) {
                        var option = $("<option value='" + arr[i].id + "'>" + arr[i].id + "</option>");
                        $('.selectpoint select').append(option);
                    }
                    $('.selectpoint select option').eq($('.selectpoint select option').length - 1).prop('selected',true);
                    setDimensionAjax();
                }

                $('.selectpoint select').off('change');
                // 当打点列表发生变化的时候
                $('.selectpoint select').on('change', function () {
                    setDimensionAjax();
                });

                function setDimensionAjax() {
                    $('.content section').remove();
                    var plugId = $('.selectapp select').val();
                    var abilityId = $('.selectpoint select').val();
                    if (abilityId == "") {
                        return;
                    }
                    $.ajax({
                        url: 'api/dimension/getDimensionInfo.do',
                        method: 'POST',
                        data: {
                            plugId: plugId,
                            abilityId: abilityId
                        },
                        success: function (data) {
                            $('.sampleRate input').val(data.data.data[abilityId]["sample"]);
                            setDimensionData(data.data.data, abilityId);
                        }
                    });
                }

                // 根据app及打点列表获取的数据，创建下面的具体数据
                function setDimensionData(data, abilityId) {
                    if (data == {} || data == null) {
                        return;
                    }
                    $('.noData').hide();
                    var max = "";
                    var min = "";
                    var obj = data[abilityId]["dimensionMeasure"];
                    $.each(obj, function (key, obj) {

                        if (!obj.measures) {
                            max = obj.max;
                            min = obj.min;
                        }else{
                            var liIndex = 0;
                            var ul = $('<ul class="items"></ul>');
                            max = "";
                            min = "";
                            $.each(obj.measures, function (keyChild, value) {
                                liIndex++;
                                setUl(liIndex, keyChild, value.min, value.max, ul);
                            });
                        }

                        setSection(key, max, min, obj.count);
                        section.append(ul);
                        $('main .content').append(section);
                    })
                }

                $(document).off('click', 'main .upplane .add');
                // 添加维度按钮
                $(document).on('click', 'main .upplane .add', function () {
                    msgflag = 'add';
                    $('.msgPlane').show();
                });

                $(document).off('click', '.msgPlane');
                // 面板消失
                $(document).on('click', '.msgPlane', function () {
                    $('.msgPlane').hide();
                    initMsg();
                });
                $(document).off('click', '.innerPlane');
                $(document).on('click', '.innerPlane', function (e) {
                    e.stopPropagation();
                });

                $(document).off('click', '.tableAdd');
                // 添加tr按钮
                $(document).on('click', '.tableAdd', function () {
                    if ($('#min').val() != "" || $('#max').val() != "") {
                        alert("维度最大值最小值不为空，不能添加指标！");
                        return;
                    }
                    $('.msgPlane .innerPlane table').append(
                        $('<tr>' +
                            '<td><input type="text" /></td>' +
                            '<td><input type="number" /></td>' +
                            '<td><input type="number" /></td>' +
                            '<td><button class="tableDel">Del</button></td>' +
                            '</tr>')
                    );
                    $('#max').prop('disabled', true);
                    $('#min').prop('disabled', true);
                });

                $(document).off('click', '.msgPlane .tableDel');
                // 删除tr按钮
                $(document).on('click', '.msgPlane .tableDel', function () {
                    $(this).parent().parent().remove();
                    if ($('.msgPlane .innerPlane table tr').length == 1) {
                        $('#max').prop('disabled', false);
                        $('#min').prop('disabled', false);
                    }
                });

                $('.confirmBtn').off('click');
                // 确定按钮
                $('.confirmBtn').on('click', function () {

                    if($('#dimension').val().trim() == "") {
                        alert("维度不能为空");
                        return;
                    }
                    if ($('#min') == "" && $('#max') !="" || $('#min') != "" && $('#max') =="") {
                        alert("维度的最大值最小值必须同时存在！");
                        return;
                    }

                    setSection($('#dimension').val(), $('#min').val(), $('#max').val(), $('#count').val());

                    if ($('#min').val() == "" && $('#max').val() == "") {
                        var ul = $('<ul class="items"></ul>');
                        for (var i = 1; i < $('.msgPlane .innerPlane table tr').length; i++) {
                            var tr = $('.msgPlane .innerPlane table tr')[i];

                            setUl(i, $(tr).find('td').eq(0).find('input').val(), $(tr).find('td').eq(1).find('input').val(), $(tr).find('td').eq(2).find('input').val(), ul);

                            section.append(ul);
                        }
                    }

                    if (msgflag == 'add') {
                        $('main .content').append(section);
                    }
                    if (msgflag == 'editor') {

                        if ($('main .content section').length -1 == editorIndex) {
                            $('main .content section').eq(editorIndex).remove();
                            $('main .content').append(section);
                        }else{
                            $('main .content section').eq(editorIndex).remove();
                            $('main .content section').eq(editorIndex).before(section);
                        }

                    }

                    initMsg();
                    $('.msgPlane').hide();
                    $('.noData').hide();
                });

                $(document).off('click', '.sectionEditor');
                // 编辑按钮
                $(document).on('click', '.sectionEditor', function () {
                    var thisSection = $(this).parent().parent();
                    var content = $('.msgPlane-content');
                    msgflag = 'editor';
                    editorIndex = thisSection.index('section');
                    content.find('#dimension').val(thisSection.find('h3').find('span').eq(0).html());
                    content.find('#min').val(thisSection.find('h3').find('.dimensionMin').html());
                    content.find('#max').val(thisSection.find('h3').find('.dimensionMax').html());
                    content.find('#count').val(thisSection.find('h3').find('.dimensionCount').html());

                    if (thisSection.find('.items').length != 0) {
                        $('#max').prop('disabled', true);
                        $('#min').prop('disabled', true);
                        for	(var i = 0; i < thisSection.find('.items li').length; i++) {
                            var li = thisSection.find('.items li')[i];
                            var data1 = $(li).find('.tr').eq(1).find('.params').html();
                            var data2 = $(li).find('.tr').eq(1).find('.min').html();
                            var data3 = $(li).find('.tr').eq(1).find('.max').html();
                            content.find('table').append($('<tr>' +
                                '<td><input type="text" value="' + data1 + '" /></td>' +
                                '<td><input type="number" value="' + data2 + '" /></td>' +
                                '<td><input type="number" value="' + data3 + '" /></td>' +
                                '<td><button class="tableDel">Del</button></td>' +
                                '</tr>')
                            );
                        }
                    }

                    $('.msgPlane').show();
                });

                $(document).off('click', '.sectionDel');
                // 删除section按钮
                $(document).on('click', '.sectionDel', function () {
                    $(this).parent().parent().remove();

                    if ($('main .content section').length == 0) {
                        $('.noData').show();
                    }
                });

                $(document).off('click', '.save');
                // 保存按钮
                $(document).on('click', '.save', function () {
                    var plugId = $('.selectapp select').val();
                    var abilityId = $('.selectpoint select').val();
                    if (plugId == "") {
                        alert("请选择app");
                        return;
                    }
                    var dimension = {};
                    dimension.sample = $('.sampleRate input').val();
                    dimension.dimensionMeasure = {};
                    for (var i = 0; i < $('.content section').length; i++) {
                        var sectionIndex = $('.content section')[i];
                        var dimensionIndex = {};
                        dimensionIndex['count'] = $(sectionIndex).find('.dimensionCount').html();

                        if ($(sectionIndex).find('.dimensionMin').html() == "" && $(sectionIndex).find('.dimensionMax').html() == 0) {
                            dimensionIndex['measures'] = {	};
                            for (var j = 0; j < $(sectionIndex).find('li').length; j++) {
                                var anyLi = $(sectionIndex).find('li')[j];
                                dimensionIndex['measures'][$(anyLi).find('.params').html()] = {
                                    'min': $(anyLi).find('.min').html(),
                                    'max': $(anyLi).find('.max').html()
                                }
                            }
                        }else{
                            dimensionIndex['min'] = $(sectionIndex).find('.dimensionMin').html();
                            dimensionIndex['max'] = $(sectionIndex).find('.dimensionMax').html();
                        }

                        dimension.dimensionMeasure[$(sectionIndex).find('h3').find('span').eq(0).html()] = dimensionIndex;
                    }
                    var data = {
                        plugId: plugId,
                        abilityId: abilityId,
                        dimension: JSON.stringify(dimension)
                    };
                    $.ajax({
                        url: 'api/dimension/insertDimension.do',
                        method: 'POST',
                        data: data,
                        success: function (res) {
                            if (res.success) {
                                alert('保存成功！');
                                setPointAjax($('.selectapp select'));
                            }
                        }
                    });

                });
            })
        }
    };

    /**
     * 公众号组件
     * @type {
    *   {
    *       template: string,
    *       data: pubTab.data,
    *       mounted: pubTab.mounted,
    *       methods: {
    *           myhttp: pubTab.methods.myhttp,
    *          getSinglePageData: pubTab.methods.getSinglePageData,
    *          createNav: pubTab.methods.createNav,
    *          allChoose: pubTab.methods.allChoose,
    *          addModal: pubTab.methods.addModal,
    *          changeModal: pubTab.methods.changeModal
    *          }
    *   }
    *          }
     */
    var PubTab = {
        template: "#pubTab",
        data: function() {
            return{
                pubData: [],
                ajaxFlag: true,
                direction: "right",
                allChooseFalse: false,
                configIdList: [],
                allModule:[],
                moduleObj: null
            };
        },
        mounted: function () {
            $('.main .main-left li').removeClass('active');
            $('.main .main-left li').eq(1).addClass('active');
            this.myhttp(1, 1);
            this.getSinglePageData();
        },
        methods: {
            /*
             *   ajax函数
             * */
            myhttp: function (pageNo, startIndex) {
                var _this = this;
                $.ajax({
                    url: "api/plug/getPlugList.do",
                    type: "POST",
                    data: {
                        pageNo: pageNo,
                        pageSize: 10
                    },
                    async: true,
                    success: function (data) {
                        console.log(data);
                        _this.pubData = data.data;
                        if (_this.ajaxFlag) {
                            _this.createNav(data.totalCount, startIndex);
                        }

                    },
                    error: function (data) {
                        console.log(data.code);
                    }
                });

            },
            /*
             *   分页申请数据
             * */
            getSinglePageData: function () {
                var _this = this;
                $(document).on("click", "#pub .my-nav li", function () {
                    var activeLiIndex = $("#pub .my-nav li.active").index();

                    if ($(this).index() > 0 && $(this).index() < $("#pub .my-nav li").length - 1) {
                        activeLiIndex = $(this).index();
                    }
                    if ($(this).index() === 0) {
                        if (activeLiIndex == 1) {
                            return;
                        }
                        activeLiIndex--;
                    }
                    if ($(this).index() === $("#pub .my-nav li").length - 1) {
                        if (activeLiIndex == $("#pub .my-nav li").length - 2) {
                            return;
                        }
                        activeLiIndex++;
                    }

                    if (activeLiIndex === 1) {
                        $("#pub .my-nav li").eq(0).addClass("disabled");
                    }else {
                        $("#pub .my-nav li").eq(0).removeClass("disabled");
                    }
                    if (activeLiIndex === $("#pub .my-nav li").length - 2) {
                        $("#pub .my-nav li").eq($("#pub .my-nav li").length - 1).addClass("disabled");
                    }else {
                        $("#pub .my-nav li").eq($("#pub .my-nav li").length - 1).removeClass("disabled");
                    }

                    $("#pub .my-nav li").removeClass("active");
                    $("#pub .my-nav li").eq(activeLiIndex).addClass("active");
                    if ($("#pub .my-nav li.active a").html() === "...") {
                        _this.ajaxFlag = true;

                        if ($("#pub .my-nav li.active").index() === 2) {
                            _this.direction = "left";
                            var  leftHtml = $("#pub .my-nav li").eq(activeLiIndex + 1).find("a").html();
                            _this.myhttp(leftHtml - 1, leftHtml - 10);
                        }else{
                            _this.direction = "right";
                            var rightHtml = $("#pub .my-nav li").eq(activeLiIndex - 1).find("a").html();
                            _this.myhttp(rightHtml / 1 + 1, rightHtml / 1 + 1);
                        }

                        return;
                    }
                    _this.myhttp($("#pub .my-nav li.active a").html() - 0);
                });
            },
            /*
             *   创建分页导航
             * */
            createNav: function (allCount,startIndex) {
                this.ajaxFlag = false;
                var myNav = $("#pub .my-nav");
                var activeClass = "active";

                if (startIndex === 1 && this.direction === "right") {
                    activeClass = "active";
                }else {
                    activeClass = "";
                }

                myNav.find("ul").empty();
                myNav.find("ul").append($("<li><a href='javascript:void(0)'>&laquo;</a></li>"));
                myNav.find("ul").append($("<li class='"+ activeClass +"'><a href='javascript:void(0)'>1</a></li>"));

                if (startIndex !== 1) {
                    myNav.find("ul").append($("<li><a href='javascript:void(0)'>...</a></li>"));
                }

                for (var i = startIndex; i <= startIndex + 9; i++) {

                    if (i === 1 || i >=  Math.ceil( allCount / 10)) {
                        continue;
                    }

                    var oLi = $("<li><a href='javascript:void(0)'>" + i + "</a></li>");

                    if ((this.direction === "left" && i % 10 === 0) || (this.direction === "right" && i % 10 === 1)) {
                        oLi.addClass("active");
                    }

                    myNav.find("ul").append(oLi);
                }

                if (startIndex + 10 < Math.ceil(allCount / 10)) {
                    myNav.find("ul").append($("<li><a href='javascript:void(0)'>...</a></li>"));
                }
                if (Math.ceil(allCount / 10) !== 1) {
                    myNav.find("ul").append($("<li><a href='javascript:void(0)'>" + Math.ceil(allCount / 10) + "</a></li>"));
                }
                myNav.find("ul").append($("<li><a href='javascript:void(0)'>&raquo;</a></li>"));
            },
            allChoose: function () {
                this.allChooseFalse = !this.allChooseFalse;
            },
            addModal: function () {
                $("#myModal input").val("");
                $("#myModal").modal();
            },
            changeModal: function (val) {
                $("#id").val(val.id);
                $('#plugName').val(val.plugName);
                $('#debug').val(val.base.debug);
                $('#uploadDuration').val(val.base.uploadDuration);
                $('#dataCache').val(val.base.cacheSize);
                $('#myswitch').val(val.base.switch);

                $("#myModal").modal();
            },
            getModule: function () {
                var selectIndex = $("#configSelect option:selected").index() - 1;
                this.moduleObj = this.allModule[selectIndex];
            },
            save: function () {

                var id = $("#id").val();
                console.log(typeof id);
                var plugName = $("#plugName").val();
                var debug = parseInt($("#debug").val());
                var uploadDuration = parseInt($("#uploadDuration").val());
                var dataCache = parseInt($("#dataCache").val());
                var myswitch = parseInt($("#myswitch").val());
                var _this = this;
                $.ajax({
                    url: "api/plug/insertPlug.do",
                    type: "POST",
                    async: true,
                    data: {
                        plugId: id,
                        plugName: plugName,
                        base: JSON.stringify({
                            debug: debug,
                            uploadDuration: uploadDuration,
                            cacheSize: dataCache,
                            switch: myswitch
                        })
                    },
                    success: function (data) {
                        console.log(data);
                        $("#myModal").modal("hide");
                        _this.myhttp(1, 1);
                    },
                    error: function (data) {
                        console.log(data.code)
                    }
                });
            },
            del: function () {
                var id = this.pubData[$("input[name='delRadio']:checked").parent().parent().index()].id;
                if (!id) {
                    return;
                }
                var res = confirm("确认删除么？");
                if (!res) {
                    return;
                }
                var _this = this;
                $.ajax({
                    url: "api/plug/deletePlug.do",
                    type: "POST",
                    async: true,
                    data: {
                        plugId: id
                    },
                    success: function (data) {
                        console.log(data);
                        _this.myhttp(1, 1);
                        alert("删除成功！");
                    },
                    error: function (data) {
                        console.log(data.code)
                    }
                });
            }
        }
    };

    /**
     *  路由
     *  routes
     *  router
     */
    var routes = [
        {path: "/CollectionConfig", component: ScadaTab},
        {path: "/App", component: PubTab},
        {path: "*", redirect: "/CollectionConfig"}
    ];
    var router = new VueRouter({
        routes: routes
    });

    /**
     *  vue实例
     *  tabChangeActive
     */
    var vm = new Vue({
        router:router,
        el: ".box",
        data: {
            routerList: ["CollectionConfig", "App"],
            tabNameList: ["CollectionConfig", "App"],
            mainRightTitle: "CollectionConfig"
        },
        mounted: function () {
            $('.username').eq(0).html(sessionStorage.getItem('username'));
            debugger;
        },
        methods: {
            go: function (value, index) {
                this.mainRightTitle = this.tabNameList[index];
                this.$router.push({path: '/' + value});
            },
            quit: function () {
                var res = confirm("确认退出？");
                if (res) {
                    var token = localStorage.getItem('token');
                    var appName = localStorage.getItem('appName');
                    $.ajax({
                        url:"api/userCol/logout.do",
                        method: "POST",
                        async: true,
                        data: {
                            token: token,
                            appName: appName
                        },
                        success: function (res) {
                            console.log(res);
                            if (res.data.errorCode == 200) {
                                window.location.href = "login.do";
                            }
                        },
                        error: function (err) {
                            console.log(err);
                        }
                    });
                }

            }
        }
    })


});
