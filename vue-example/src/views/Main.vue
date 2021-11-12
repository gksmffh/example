<template>
  <div>
    <button type="button" @click="postLogOut">LogOut</button>
    <input
      type="text"
      v-model="keyword"
      class="form-control"
      placeholder="제목"
      aria-label="제목"
      aria-describedby="addon-wrapping"
    />
    <button type="button" @click="search">검색</button>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">TITLE</th>
          <th scope="col">REG_DATE</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(board, key) in list" :key="key">
          <th scope="row">{{ board.boardSeq }}</th>
          <td>
            <a @click="detail(board.boardSeq)" style="cursor: pointer">{{
              board.title
            }}</a>
          </td>
          <td>{{ changeDate(board.regDate / 1000) }}</td>
        </tr>
      </tbody>
    </table>
    <button type="button" @click="putAdd">Add</button>
  </div>
</template>
<script>
import axios from "axios";
import VueCookies from "vue-cookies";
export default {
  name: "",
  components: {},
  data() {
    return {
      keyword: "",
      list: [],
    };
  },
  setup() {},
  created() {
    this.getData();
  },
  mounted() {},
  unmounted() {},
  methods: {
    async getData() {
      try {
        const response = await axios.get("http://localhost:8080/board", {
          headers: { Authorization: `Bearer ${VueCookies.get("token")}` },
        });
        console.log(response);
        this.list = response.data.data;
      } catch (error) {
        alert(error.response.data.error);
      }
    },
    changeDate(t) {
      var myDate = new Date(t * 1000);
      var date =
        myDate.getFullYear() +
        "-" +
        (myDate.getMonth() + 1) +
        "-" +
        myDate.getDate() +
        " " +
        myDate.getHours() +
        "시" +
        myDate.getMinutes() +
        "분";
      return date;
    },
    postLogOut() {
      VueCookies.remove("token");
      this.$router.push({
        path: "/signin",
      });
    },
    putAdd() {
      this.$router.push({
        path: "/add",
      });
    },
    detail(boardSeq) {
      this.$router.push({
        path: `/detail/${boardSeq}`,
      });
    },
    async search() {
      try {
        const response = await axios.get("http://localhost:8080/board", {
          params: { keyword: this.keyword },
          headers: { Authorization: `Bearer ${VueCookies.get("token")}` },
        });
        console.log(response);
        this.list = response.data.data;
      } catch (error) {
        alert(error.response.data.error);
      }
    },
  },
};
</script>
