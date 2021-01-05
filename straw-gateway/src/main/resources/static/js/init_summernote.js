$(document).ready(function () {
    $('#summernote').summernote({
        height: 300,
        tabsize: 2,
        lang: 'zh-CN',
        placeholder: '请输入问题的详细描述...',
        callbacks: {
            onImageUpload: function (files) {
                // alert('即将自行处理上传本地文件……');
                if (files.length == 0) {
                    alert('请选择图片！');
                    return;
                }
                if (files.length > 1) {
                    alert('每次只允许选择1张图片！');
                    return;
                }
                let file = files[0];
                let data = new FormData();
                data.append("imageFile", file);
                // 在使用$.ajax()实现文件上传时，data必须是FormData对象，且必须配置contentType:false和processData:false
                $.ajax({
                    url: '/resource/v1/upload/image',
                    data: data,
                    type: 'post',
                    contentType: false,
                    processData: false,
                    success: function (r) {
                        if (r.state == 2000) {
                            //alert('上传成功！');
                            let img = new Image();
                            img.src=r.data;
                            $('#summernote').summernote('insertNode',img);
                        } else {
                            alert(r.message);
                        }
                    }
                });
            }
        }
    });
});