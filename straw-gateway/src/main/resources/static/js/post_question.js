Vue.component('v-select',VueSelect.VueSelect);
let postQuestionApp = new Vue({
    el:'#postQuestionApp',
    data:{
        title:null,
        tags:[],
        selectedTagIds:[],
        teachers:[
            {value:1,label:'WangKJ'},
            {value:2,label:'FanCQ'},
            {value:3,label:'LiuGB'},
            {value:4,label:'ChengH'},
            {value:5,label:'ChenZH'}
        ],
        selectedTeacherIds:[]
    },
    methods:{
        loadTags:function (){
        $.ajax( {
            url:'/redis-tag/v1/tags',
            success:function (r){
                let list = r.data;
                let tags = [];
                for (let i = 0; i < list.length; i++) {
                    let op={};
                    op.label=list[i].name;
                    op.value=list[i].id;
                    tags[i]=op;
                }
                postQuestionApp.tags=tags;
            }
        });
     },
        loadTeachers:function (){
            //alert("..................");
            $.ajax( {
                url:'/redis-teacher/v1/users/teachers',
                success:function (r){
                    let list = r.data;
                    let teachers = [];
                    for (let i = 0; i < list.length; i++) {
                        let op={};
                        op.label=list[i].nickName;
                        op.value=list[i].id;
                        teachers[i]=op;
                    }
                    postQuestionApp.teachers=teachers;
                }
            });
        },
        postQuestion:function (){
            let data={
                'title':postQuestionApp.title,
                'content':$('#summernote').val(),
                'tagIds':postQuestionApp.selectedTagIds,
                'teacherIds':postQuestionApp.selectedTeacherIds
            }
            console.log('即将提交到服务器端的请求参数:');
            console.log(data);
            $.ajax({
                url:'/api-question/v1/questions/post',
                data:data,
                type:'post',
                traditional: true,
                success:function (r){
                    if (r.state==2000){
                        alert('发送问题成功!');
                    }else{
                        alert(r.message);
                    }
                }
            });
        }
    },
    created:function (){
        this.loadTags();
        this.loadTeachers();
    }
});