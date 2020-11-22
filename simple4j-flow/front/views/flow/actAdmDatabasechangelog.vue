<template>
  <div class="app-container">
    <avue-crud :before-open='beforeOpen'
               :data='data'
               :option='option'
               :page='page'
               :permission='permissionList'
               :table-loading='loading'
               @current-change='currentChange'
               @on-load='onLoad'
               @row-del='rowDel'
               @row-save='rowSave'
               @row-update='rowUpdate'
               @search-change='searchChange'
               @search-reset='searchReset'
               @selection-change='selectionChange'
               @size-change='sizeChange'
               ref='crud'
               v-model='form'>
      <template slot='menuLeft'>
        <el-button @click='handleDelete'
                   icon='el-icon-delete'
                   plain
                   size='small'
                   type='danger'
                   v-if='permissions.actadmdatabasechangelog_delete'>删 除
        </el-button>
      </template>
    </avue-crud>
  </div>
</template>

<script>
  import {add, getDetail, getPage, remove, update} from '@/api/flow/actAdmDatabasechangelog';
  import {mapGetters} from 'vuex';

  export default {
    data() {
      return {
        form: {},
        query: {},
        loading: true,
        page: {
          pageSize: 10,
          currentPage: 1,
          total: 0
        },
        selectionList: [],
        option: {
          height: 'auto',
          calcHeight: 210,
          searchShow: true,
          searchMenuSpan: 6,
          tip: false,
          border: true,
          index: true,
          viewBtn: true,
          selection: true,
          column: [
            {
              label: '',
              prop: 'ID',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'AUTHOR',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'FILENAME',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'DATEEXECUTED',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'ORDEREXECUTED',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'EXECTYPE',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'MD5SUM',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'DESCRIPTION',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'COMMENTS',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'TAG',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'LIQUIBASE',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'CONTEXTS',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'LABELS',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
            {
              label: '',
              prop: 'DEPLOYMENT_ID',
              rules: [{
                required: true,
                message: '请输入',
                trigger: 'blur'
              }]
            },
          ]
        },
        data: []
      };
    },
    computed: {
      ...mapGetters(['permissions']),
      permissionList() {
        return {
          addBtn: this.vaildData(this.permissions.actadmdatabasechangelog_add, false),
          viewBtn: this.vaildData(this.permissions.actadmdatabasechangelog_view, false),
          delBtn: this.vaildData(this.permissions.actadmdatabasechangelog_delete, false),
          editBtn: this.vaildData(this.permissions.actadmdatabasechangelog_edit, false)
        };
      },
      ids() {
        let ids = [];
        this.selectionList.forEach(ele => {
          ids.push(ele.id);
        });
        return ids;
      }
    },
    methods: {
      rowSave(row, done, loading) {
        add(row).then(() => {
          done();
          this.onLoad(this.page);
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        }, error => {
          window.console.log(error);
          loading();
        });
      },
      rowUpdate(row, index, done, loading) {
        update(row).then(() => {
          done();
          this.onLoad(this.page);
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        }, error => {
          window.console.log(error);
          loading();
        });
      },
      rowDel(row) {
        this.$confirm('确定将选择数据删除?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        .then(() => {
          return remove([row.id]);
        })
        .then(() => {
          this.onLoad(this.page);
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        });
      },
      handleDelete() {
        if (this.selectionList.length === 0) {
          this.$message.warning('请选择至少一条数据');
          return;
        }
        this.$confirm('确定将选择数据删除?', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        .then(() => {
          return remove(this.ids);
        })
        .then(() => {
          this.onLoad(this.page);
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
          this.$refs.crud.toggleSelection();
        });
      },
      beforeOpen(done, type) {
        if (['edit', 'view'].includes(type)) {
          getDetail(this.form.id).then(res => {
            this.form = res.data.data;
          });
        }
        done();
      },
      searchReset() {
        this.query = {};
        this.onLoad(this.page);
      },
      searchChange(params, done) {
        this.query = params;
        this.page.currentPage = 1;
        this.onLoad(this.page, params);
        done();
      },
      selectionChange(list) {
        this.selectionList = list;
      },
      selectionClear() {
        this.selectionList = [];
        this.$refs.crud.toggleSelection();
      },
      currentChange(currentPage) {
        this.page.currentPage = currentPage;
      },
      sizeChange(pageSize) {
        this.page.pageSize = pageSize;
      },
      onLoad(page, params = {}) {
        this.loading = true;
        getPage(page.currentPage, page.pageSize, Object.assign(params, this.query)).then(
            res => {
              const data = res.data.data;
              this.page.total = data.total;
              this.data = data.records;
              this.loading = false;
              this.selectionClear();
            });
      }
    }
  };
</script>

<style>
</style>
