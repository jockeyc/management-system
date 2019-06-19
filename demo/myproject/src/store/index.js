import  Vue from 'vue'
import  Vuex from 'vuex'

Vue.use(Vuex);

const store=new Vuex.Store({
  state:{
    semester:'2012-2013冬季',
    Info:{}
  },
  getters:{
  }
  ,
  mutations: {
    setInfo(state,info){
      state.Info=info;
      sessionStorage.setItem("info",JSON.stringify(info));
    }
  },
  actions:{
    setInfoFun(context,info){
      context.commit("setInfo",info);
    }
  }
});
export default store
