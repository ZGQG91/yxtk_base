var left={};
$(function(){
    left.init=function(){
        App.init();
        var content=$('.content');
        var configDiv=$('<div class="configDiv"></div>');
        content.append(configDiv);
        left.initFirst();
        $('.upContent').css('display','block');
        $('.upload').click(function(){
            left.configInitInfo(this,configDiv,'block');
        });
        $('.viewConfig').click(function(){
            left.configInitInfo(this,configDiv,'none');
            configDiv.append('<div class="form-group"><label for="exampleInputEmail1">上传名称</label><input type="text" name="repoName" class="form-control"/>比如（yxtk-test2）<br/></div>');
            configDiv.append('<div class="form-group"><label for="exampleInputEmail1">白名单设置</label><input type="text" name="whiteDomains" class="form-control"/>比如（["http://api.readyidu.com","https://api.readiydu.com"]）<br/></div>');
            configDiv.append('<div class="form-group"><label for="exampleInputEmail1">模型路径</label><input type="text" name="modulePath" class="form-control"/>比如（{"smart-edit":"","yxtk-yun":""}）<br/></div>');
            configDiv.append('<input type="button" value="配置" class="configPro btn btn-primary"/><br/>');
            left.config();
        });
    }
    left.configInitInfo=function(obj,configDiv,upContentCss){
        left.active(obj);
        configDiv.empty();
        $('.upContent').css('display',upContentCss);
    }
    left.active=function(obj){
        $(obj).parent('ul').children('li').removeClass("active");
        $(obj).addClass("active");
    }
    left.upload=function(){

        $("#drop").dropzone({
            url: "upload/upload/file.do",
            addRemoveLinks: true,
            dictRemoveLinks: "x",
            dictCancelUpload: "x",
            maxFiles: 10,
            maxFilesize: 50,
            acceptedFiles: ".zip",
            init: function() {
                this.on("success", function(file) {
                    console.log("File " + file.name + "uploaded");
                });
                this.on("removedfile", function(file) {
                    console.log("File " + file.name + "removed");
                });
            }
        });

    }
    left.initFirst=function(){
        left.selectMethod();
        left.configInfo();
        dropinit.init();
        left.upload();
    }
    left.selectMethod=function(){
        $('select.drop-select').each(function(){
            new Select({
                el: this,
                selectLikeAlignment: $(this).attr('data-select-like-alignement') || 'auto',
                className: 'select-theme-dark'
            });
        });
    }
    left.configInfo=function(){
        $.ajax({
            url : "api/business/getConfigList.do",
            type : "POST",
            data : {
                pageNo:1,
                pageSize:20,
                userId:1001
            },
            async: true,
            success : function(data) {
                var result=data.data;
                $('.drop-select').empty();
                for(var i=0;i<result.length;i++){
                    $('.drop-select').append('<option value="'+result[i].id+'">'+result[i].repoName+'</option>');
                }
                $('.drop-select').change(function(){
                    $('[name="configId"]').val($('.drop-select option:selected').val())
                })
            },
            error : function(data) {

            }
        });
    }
    left.config=function(){
        $('.configPro').click(function(){
            var repoName=$('[name="repoName"]').val();
            var whiteDomains=$('[name="whiteDomains"]').val();
            var modulePath=$('[name="modulePath"]').val();
            $.ajax({
                url : "api/business/configProject.do",
                type : "POST",
                data : {
                    repoName:repoName,
                    whiteDomains:whiteDomains,
                    modulePath:modulePath
                },
                async: true,
                success : function(data) {
                    alert('配置成功');
                },
                error : function(data) {

                }
            });
        });
    }
    left.init();

});
