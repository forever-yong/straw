let tagsApp=new Vue({
    el:'#tagsApp',
    data:{
        tags:[]
    },
    methods:{
        loadTags:function (){
            //alert('准备从服务器加载标签列表......');
            $.ajax({
                url:'/portal/tags',
                success:function (r){
                    tagsApp.tags=r.data;
                }
            });
        }
    },
    created:function (){
        this.loadTags();
    }
});