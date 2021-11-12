<template>
  <div>
    <h2>로그인</h2>
    <input placeholder="Enter your ID" v-model="uid" />
    <input placeholder="Enter your password" v-model="password" />
    <button type="button" @click="postLogin">Login</button>
    <router-link to="/signup">
      <button type="button">Signup</button>
    </router-link>
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
      uid: "",
      password: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    async postLogin() {
      try {
        const data = {
          username: this.uid,
          password: this.password,
        };
        const response = await axios.post(
          "http://localhost:8080/api/auth/signin",
          data
        );
        console.log(response);

        VueCookies.set("token", response.data.accessToken);

        alert("Success");
        this.$router.push({
          path: "/main",
        });
      } catch (error) {
        alert(error.response.data.error);
      }
    },
  },
};
</script>
