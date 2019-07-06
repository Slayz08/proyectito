<template>
  <v-layout align-start>
    <v-flex>
      <v-toolbar flat color="white">
        <v-toolbar-title>Actividades</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-text-field
          class="text-xs-center"
          v-model="search"
          append-icon="search"
          label="Búsqueda"
          single-line
          hide-details
        ></v-text-field>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <v-btn slot="activator" color="primary" dark class="mb-2">Nuevo</v-btn>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container grid-list-md>
                <v-layout wrap>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="local" label="Local"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="dateActivity" label="DateActivity"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="theme" label="Theme"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="quantityPeople" label="QuantityPeople"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="imageUrl" label="ImageUrl"></v-text-field>
                  </v-flex>
                </v-layout>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" flat @click.native="close">Cancelar</v-btn>
              <v-btn color="blue darken-1" flat @click.native="guardar">Guardar</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
      <v-data-table :headers="headers" :items="actividades" :search="search" class="elevation-1">
        <template slot="items" slot-scope="props">
          <td class="justify-center layout px-0">
            <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          
          </td>
          <td>{{ props.item.id }}</td>
          <td>{{ props.item.local }}</td>
          <td>{{ props.item.dateActivity }}</td>
          <td>{{ props.item.theme }}</td>
          <td>{{ props.item.quantityPeople }}</td>
          <td>{{ props.item.imageUrl }}</td>
        </template>
        <template slot="no-data">
          <v-btn color="primary" @click="listar">Resetear</v-btn>
        </template>
      </v-data-table>
    </v-flex>
  </v-layout>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      actividades: [],
      dialog: false,
      headers: [
        { text: "Opciones", value: "opciones", sortable: false },
        { text: "Id", value: "id", sortable: false },
        { text: "Local", value: "local", sortable: false },
        { text: "DateActivity", value: "dateActivity", sortable: false },
        { text: "Theme", value: "theme" },
        { text: "QuantityPeople", value: "quantityPeople" },
        { text: "ImageUrl", value: "imageUrl" }
      ],
      search: "",
      editedIndex: -1,

      //Model
      id:"",
      local:"",
      dateActivity:"",
      theme:"",
      quantityPeople: "",
      imageUrl: ""
    };
  },
  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Nuevo Paciente" : "Actualizar Paciente";
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.listar();
  },
  methods: {
    listar() {
      let me = this;
      axios
        .get("api/activity")
        .then(function(response) {
          //console.log(response);
          me.actividades = response.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    editItem(item) {
      this.id = item.id;
      this.local = item.local;
      this.dateActivity = item.dateActivity;
      this.theme = item.theme;
      this.quantityPeople = item.quantityPeople;
      this.imageUrl = item.imageUrl;

      this.editedIndex = 1;
      this.dialog = true;
    },

  

    close() {
      this.dialog = false;
    },
    limpiar() {
      this.id = "";
      this.local = "";
      this.dateActivity = "";
      this.theme = "";
      this.quantityPeople = "";
      this.imageUrl = "";
    },
    guardar() {
      if (this.editedIndex > -1) {
        //Código para editar

        let me = this;
        axios 
          .put("api/activity", {
            id: me.id,
            local: me.local,
            dateActivity: me.dateActivity,
            theme: me.theme,
            quantityPeople: me.quantityPeople,
            imageUrl: me.imageUrl
          })
          .then(function(response) {
            me.close();
            me.listar();
            me.limpiar();
          })
          .catch(function(error) {
            console.log(error);
          });
      } else {
        //Código para guardar
        let me = this;
        axios
          .post("api/activity", {
            local: me.local,
            dateActivity: me.dateActivity,
            theme: me.theme,
            quantityPeople: me.quantityPeople,
            imageUrl: me.imageUrl
          })
          .then(function(response) {
            me.close();
            me.listar();
            me.limpiar();
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    }
  }
};
</script>
