<template>
  <div class="user">
    <h1 class="user-title">Кабинет</h1>
    <div class="user-container">
      <h1 class="name">
        {{ this.response.first_name + " " + this.response.last_name }}
      </h1>
      <h2 class="email">{{ this.response.email }}</h2>
    </div>
    <div class="img-container">
      <img src="@/assets/cat-in-car.png" alt="" />
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";

export default {
  data() {
    return {
      response: {},
    };
  },
  created() {
    AXIOS.get(
      "https://car-store-backend-05sq.onrender.com/car-store/users/" +
        localStorage.getItem("userId"),
      {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      }
    )
      .then((response) => {
        this.response = response.data;
      })
      .catch((e) => {
        if (localStorage.getItem("token")) {
          alert("Время вашей сессии истекло!");
          localStorage.removeItem("token");
        }
      });
  },
};
</script>

<style lang="scss" scoped>
.user {
  font-family: Montserrat;
  color: #fff;
  .user-title {
    color: #ff9131;
    font-size: 32px;
    font-weight: 800;
  }
  .img-container {
    width: 60%;
    margin: 0 auto;
    img {
      width: 100%;
      border-radius: 8px;
    }
  }
}
</style>
