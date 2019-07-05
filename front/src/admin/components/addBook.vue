<template>
  <div id="addBookDiv">
    <Divider orientation="left">添加书籍</Divider>
    <Form :model="bookForm" :label-width="80" style="float: left; margin-left: 50px">
      <FormItem label="书籍封面">
        <img v-if="previewImg.src" :src="previewImg.src">
        <i v-else class="ivu-icon ivu-icon-ios-cloud-upload" style="color: #3399ff" @click="iconClick"></i>
        <input @change="uploadImg" id="file" type="file" name="file" class="el-upload__input">
      </FormItem>

      <FormItem label="书名">
        <Input v-model="bookForm.bookName" class="inputLong" placeholder="请输入您要添加书本的名字。。"/>
      </FormItem>

      <FormItem label="作者">
        <Input v-model="bookForm.bookContributors" class="inputLong" placeholder="请输入您要添加书本的作者。。"/>
      </FormItem>

      <FormItem label="所属分类">
        <Select v-model="bookForm.bookCategory" style="width:200px" @on-change="getCategoryId">
          <Option v-for="(category,index) in categories" :value="category.categoryId" :key="index">{{ category.categoryName }}</Option>
        </Select>
      </FormItem>

      <FormItem label="二级分类">
        <Select v-model="bookForm.belongSubdivision_id" clearable style="width:200px" ref="store">
          <Option v-for="subdivision in subdivisions" :value="subdivision.subdivisionId" :key="subdivision.subdivisionId">{{ subdivision.subdivisionName }}</Option>
        </Select>
      </FormItem>

      <FormItem label="章节数">
        <InputNumber :min="1" style="margin-left: -120px" v-model="bookForm.chapterNumber"></InputNumber>
      </FormItem>

      <FormItem label="版次">
        <InputNumber :min="1" style="margin-left: -120px" v-model="bookForm.editionNumber"></InputNumber>
      </FormItem>

      <FormItem>
          <Button type="primary" long @click="submitBookForm">Submit</Button>
      </FormItem>
    </Form>
  </div>
</template>

<script>
    export default {
      name: "",
      inject:['reload'],
      components: {},
      data() {
        return {
          value:true,
          top:"",
          bookForm: {
            bookName: "",
            bookCategory: "",
            bookContributors: "",
            belongSubdivision_id: "",
            chapterNumber: 0,
            editionNumber: 0,
          },
          bookCover: "",
          categories: "",
          subdivisions: "",
          previewImg:{
            src:"",
          },
          width:""
        }
      },
      methods: {
        iconClick(){
          document.getElementById("file").click();
        },
        getCategoryId(value) {
          this.$refs.store.clearSingleSelect()
          let category = this.categories.find(function (category) {
            return category.categoryId == value;
          });
          this.subdivisions = category.subdivisions;
        },
        uploadImg:function(event){
          var oFReader = new FileReader();
          var file=event.target.files[0];
          this.bookCover=file;

          oFReader.readAsDataURL(file);

          var obj = this;

          oFReader.onloadend=function (oFRevent) {
            var src=oFRevent.target.result;
            obj.previewImg.src=src;

            var img=new Image();
            img.src = src;
            img.onload=function(){
              obj.width =(img.width/img.height) * 100;
            }
          };
        },
        submitBookForm() {
          let formdata = new FormData();
          formdata.append("file",this.bookCover);
          formdata.append("bookForm",JSON.stringify(this.bookForm));
          this.$axios.post('/api/admin/addBook',formdata, {
            headers:{
               'Accept':'application/json',
                'Content-Type':'multipart/form-data'
            }
          }).then((response) => {
            if (response.data == true) {
              this.$Message.success('添加成功！');
              this.reload();
            }
            else {
              this.$Message.error('服务器异常，发生错误，请稍后提交:(');
              this.reload();
            }
          });

        },
      },
      created() {
        // fetch('/api/admin/addBook',{
        //   method:'post',
        // }).then(result => {
        //   console.log("dqwd")
        //   console.log(result)
        // })
        this.$axios.post('/api/index/getAllTopCategory').then(response => {
          this.categories = response.data;
        })
      },
    }
</script>

<style scope>

  #addBookDiv{
    width: 1000px;
    height: 600px;
    background-color: #FFFFFF;
  }
  .inputLong{
    width: 200px;
  }
  .layout-footer-center{
    text-align: center;
  }
</style>
