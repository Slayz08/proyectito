import Vue from 'vue'
import './plugins/vuetify'
import MultiFiltersPlugin from './plugins/MultiFilters' // <-- THIS
import App from './App.vue'
import router from './router'
import store from './store'

import axios from 'axios'

Vue.config.productionTip = false;
Vue.use(MultiFiltersPlugin);

axios.defaults.baseURL='http://localhost:5050/'
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
