let mostHitsApp = new Vue({
    el:'#mostHitsApp',
    data:{
        questions:[
            {id:1,title:'第一个问题',statusText:'已解决',statusClass:'text-success',hits:852},
            {id:2,title:'第二个问题',statusText:'未解决',statusClass:'text-info',hits:999},
            {id:3,title:'第三个问题',statusText:'未回复',statusClass:'text-warning',hits:233}
        ]
    },
    methods:{
        loadMostHitsList:function (){
           // alert('准备加载热点问题列表......');
            $.ajax({
               url:'/redis-hitsquestion/v1/questions',
                success:function (r) {
                    let list=r.data;
                    let statusTexts=['未回复','已回复','已解决'];
                    let statusClasses=['text-warning','text-info','text-success'];
                    for (let i = 0; i < list.length; i++) {
                        list[i].statusText=statusTexts[list[i].status];
                        list[i].statusClass=statusClasses[list[i].status];
                    }
                    mostHitsApp.questions=list;
                }

           });
        }
    },
    created:function () {
        this.loadMostHitsList();
    }
});