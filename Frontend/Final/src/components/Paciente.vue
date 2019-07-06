<template>
  <v-layout align-start>
    <v-flex>
      <v-toolbar flat color="white">
        <v-toolbar-title>Pacientes</v-toolbar-title>
        <v-divider class="mx-2" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <VSelect :items="TiposDeProyectos" v-model="search" label="Filtrar segun Proyecto:"></VSelect>
        <v-spacer></v-spacer>
        <v-text-field v-model="sueldoFiltro"  label="Filtrar Sueldo mayor que:"></v-text-field>
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
                    <v-text-field v-model="nombre"  label="Nombre"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="descripcion" label="Descripcion"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <VSelect
                      :items="TiposDeProyectos"
                      v-model="tipoProyecto"
                      label="Tipo de Proyecto"
                    ></VSelect>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="sueldo" label="Sueldo"></v-text-field>
                  </v-flex>
                  <v-flex xs12 sm12 md12>
                    <v-text-field v-model="estado" label="Estado"></v-text-field>
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
      <v-data-table :headers="headers" :items="pacientes" :search="search"  class="elevation-1">
        <template slot="items" slot-scope="props">
          <td class="justify-center layout px-0">
            <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          </td>
          <td>{{ props.item.id }}</td>
          <td>{{ props.item.nombre }}</td>
          <td>{{ props.item.descripcion }}</td>
          <td>{{ props.item.tipoProyecto }}</td>
          <td>{{ props.item.sueldo }}</td>
          <td>{{ props.item.estado }}</td>
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
      pacientes: [],
      TiposDeProyectos: [
        { text: "Ninguno", value: "" },
        { text: "Sistemas", value: "Sistemas" },
        { text: "Software", value: "Software" },
        { text: "Ciencias de la Computación",value: "Ciencia de la Computacion" }
      ],
      dialog: false,
      headers: [
        { text: "Opciones", value: "opciones", sortable: false },
        { text: "ID", value: "id" },
        { text: "Nombre", value: "nombre", sortable: false },
        { text: "Descripcion", value: "descripcion", sortable: false },
        { text: "Tipo de Proyecto", value: "tipoProyecto" },
        { text: "Sueldo", value: "sueldo" },
        { text: "Estado", value: "estado" }
      ],
      search: "",
      editedIndex: -1,

      //Model
      id: "",
      nombre: "",
      descripcion: "",
      tipoProyecto: "",
      sueldo: "",
      estado: ""
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
        .get("proyectos")
        .then(function(response) {
          //console.log(response);
          me.pacientes = response.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    editItem(item) {
      this.id = item.id;
      this.nombre = item.nombre;
      this.descripcion = item.descripcion;
      this.tipoProyecto = item.tipoProyecto;
      this.sueldo = item.sueldo;
      this.estado = item.estado;

      this.editedIndex = 1;
      this.dialog = true;
    },

    close() {
      this.dialog = false;
    },
    limpiar() {
      this.id = "";
      this.nombre = "";
      this.descripcion = "";
      this.tipoProyecto = "";
      this.sueldo = "";
      this.estado = "";
    },
    guardar() {
      if (this.editedIndex > -1) {
        //Código para editar

        let me = this;
        axios
          .put("proyectos", {
            id: me.id,
            nombre: me.nombre,
            descripcion: me.descripcion,
            tipoProyecto: me.tipoProyecto,
            sueldo: me.sueldo,
            estado: me.estado
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
          .post("proyectos", {
            nombre: me.nombre,
            descripcion: me.descripcion,
            tipoProyecto: me.tipoProyecto,
            sueldo: me.sueldo,
            estado: me.estado
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
