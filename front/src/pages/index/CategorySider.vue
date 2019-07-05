<!--功能完备的组件-->
<template>
    <Menu width="170" theme="light" :open-names="['1']" accordion @on-select="clickSubdivision" style="background-color: #ffffff">
      <Divider orientation="left" style="font-size: 16px">作品分类</Divider>
      <MenuItem :name="100">推荐</MenuItem>
      <Submenu :name="category.categoryId" v-for="category in categories" :key="category.id">
        <template slot="title">
          {{ category.categoryName }}
        </template>
        <MenuItem :name="subdivision.subdivisionId" v-for="subdivision in category.subdivisions" :key="subdivision.id">{{ subdivision.subdivisionName }}</MenuItem>
      </Submenu>
    </Menu>
</template>

<script>
   export default {
      name: "",
      data(){
        return{
          categories:"",
        }
      },
      methods:{
          clickSubdivision(name){
              if (name == 100){
                this.$router.push( {path: '/index/books'})
              }else {
                this.$router.push( {name: 'page', params: { subdivisionId: name}})
              }
          }
      },
      created(){
          this.$axios.post('/api/index/getAllTopCategory').then(response => {
            this.categories = response.data;
          })
      },
    }
</script>

<style scoped>
  .ivu-layout-sider{
    margin: 80px 20px 0;
    background-color: #FFFFFF;
  }


</style>
