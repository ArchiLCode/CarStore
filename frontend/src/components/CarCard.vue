<template>
  <div class="container">
    <div>
      <div class="img-container">
        <img :src="car.imgLink" alt="Car" />
      </div>
      <div class="name">
        <h2>{{ car.name }}</h2>
      </div>
      <div class="description">
        <p>{{ car.description }}</p>
      </div>
    </div>
    <div class="price">
      <h2>{{ this.splitPrice(car.price) }} &#8381;</h2>
      <button @click="addToCart()" id="add-to-cart-btn">В корзину</button>
    </div>
  </div>
</template>

<script>
import AXIOS from "@/http-common.js";

export default {
  name: "Car",
  props: { car: {} },
  data() {
    return {
      userId: localStorage.getItem("userId"),
    };
  },
  methods: {
    addToCart() {
      if (localStorage.getItem("token")) {
        AXIOS.post(
          "https://car-store-backend-05sq.onrender.com/car-store/user/" +
            this.userId +
            "/car/" +
            this.car.id,
          {},
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
            },
          }
        ).catch((e) => {
          if (localStorage.getItem("token") && e.response.status == 401) {
            alert("Время вашей сессии истекло!");
          } else if (
            localStorage.getItem("token") &&
            e.response.status == 400
          ) {
            alert("Авто уже в корзине!");
          }
        });
      } else {
        alert("Вы не авторизованы!");
      }
    },
    splitPrice(price) {
      const reversedArr = String(price).split("").reverse();
      let newString = [];
      for (let i = 0; i < reversedArr.length; i++) {
        newString.push(String(reversedArr[i]));
        if ((i + 1) % 3 === 0) {
          newString.push(" ");
        }
      }
      return newString.reverse().join("");
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 20px;
  width: 250px;
  height: 380px;
  font-family: Montserrat;
  border-radius: 20px;
  transition: 0.2s;
  background-color: #3a3a3a;
  &:hover {
    box-shadow: 0px 2px 24px 2px #f4f4f415;
  }
  img {
    width: 100%;
    border-radius: 25%;
  }
  .name {
    color: #ffffff;
    font-weight: 800;
    h2 {
      margin: 0;
      margin-bottom: 20px;
      font-size: 24px;
    }
  }
  .description {
    color: #d4d4d4;
    font-weight: 500;
    font-size: 13px;
    p {
      margin: 0;
    }
  }
  .price {
    color: #ffffff;
    font-weight: 700;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    h2 {
      font-size: 20px;
      margin: 0;
    }
  }
  #add-to-cart-btn {
    background-color: #ff9131;
    border: none;
    padding: 10px 15px;
    outline: none;
    border-radius: 8px;
    font-weight: 700;
    font-size: 14px;
    color: #fff;
    transition: 0.2s;
    cursor: pointer;
    &:hover {
      color: #473e43;
    }
  }
}
</style>
