<template>
	<basic-container>
		<avue-crud :option="option"
				   :table-loading="loading"
				   :data="data"
				   :page="page"
				   :permission="permissionList"
				   :before-open="beforeOpen"
				   v-model="form"
				   ref="crud"
				   @row-update="rowUpdate"
				   @row-save="rowSave"
				   @row-del="rowDel"
				   @search-change="searchChange"
				   @search-reset="searchReset"
				   @selection-change="selectionChange"
				   @current-change="currentChange"
				   @size-change="sizeChange"
				   @on-load="onLoad">
			<template slot="menuLeft">
				<el-button type="danger"
						   size="small"
						   icon="el-icon-delete"
						   plain
						   v-if="permission.code_delete"
						   @click="handleDelete">删 除
				</el-button>
			</template>
		</avue-crud>
	</basic-container>
</template>

<script>
	import {add, getDetail, getPage, remove, update} from "@/api/";
	import {mapGetters} from "vuex";

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
									label: "主键",
									prop: "id",
									rules: [{
										required: true,
										message: "请输入主键",
										trigger: "blur"
									}]
								},
								{
									label: "数据源主键",
									prop: "datasource_id",
									rules: [{
										required: true,
										message: "请输入数据源主键",
										trigger: "blur"
									}]
								},
								{
									label: "服务名称",
									prop: "service_name",
									rules: [{
										required: true,
										message: "请输入服务名称",
										trigger: "blur"
									}]
								},
								{
									label: "模块名称",
									prop: "code_name",
									rules: [{
										required: true,
										message: "请输入模块名称",
										trigger: "blur"
									}]
								},
								{
									label: "表名",
									prop: "table_name",
									rules: [{
										required: true,
										message: "请输入表名",
										trigger: "blur"
									}]
								},
								{
									label: "表前缀",
									prop: "table_prefix",
									rules: [{
										required: true,
										message: "请输入表前缀",
										trigger: "blur"
									}]
								},
								{
									label: "主键名",
									prop: "pk_name",
									rules: [{
										required: true,
										message: "请输入主键名",
										trigger: "blur"
									}]
								},
								{
									label: "后端包名",
									prop: "package_name",
									rules: [{
										required: true,
										message: "请输入后端包名",
										trigger: "blur"
									}]
								},
								{
									label: "基础业务模式",
									prop: "base_mode",
									rules: [{
										required: true,
										message: "请输入基础业务模式",
										trigger: "blur"
									}]
								},
								{
									label: "包装器模式",
									prop: "wrap_mode",
									rules: [{
										required: true,
										message: "请输入包装器模式",
										trigger: "blur"
									}]
								},
								{
									label: "后端路径",
									prop: "api_path",
									rules: [{
										required: true,
										message: "请输入后端路径",
										trigger: "blur"
									}]
								},
								{
									label: "前端路径",
									prop: "web_path",
									rules: [{
										required: true,
										message: "请输入前端路径",
										trigger: "blur"
									}]
								},
					]
				},
				data: []
			};
		},
		computed: {
			...mapGetters(["permission"]),
			permissionList() {
				return {
					addBtn: this.vaildData(this.permission.code_add, false),
					viewBtn: this.vaildData(this.permission.code_view, false),
					delBtn: this.vaildData(this.permission.code_delete, false),
					editBtn: this.vaildData(this.permission.code_edit, false)
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
						type: "success",
						message: "操作成功!"
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
						type: "success",
						message: "操作成功!"
					});
				}, error => {
					window.console.log(error);
					loading();
				});
			},
			rowDel(row) {
				this.$confirm("确定将选择数据删除?", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				})
				.then(() => {
					return remove([row.id]);
				})
				.then(() => {
					this.onLoad(this.page);
					this.$message({
						type: "success",
						message: "操作成功!"
					});
				});
			},
			handleDelete() {
				if (this.selectionList.length === 0) {
					this.$message.warning("请选择至少一条数据");
					return;
				}
				this.$confirm("确定将选择数据删除?", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				})
				.then(() => {
					return remove(this.ids);
				})
				.then(() => {
					this.onLoad(this.page);
					this.$message({
						type: "success",
						message: "操作成功!"
					});
					this.$refs.crud.toggleSelection();
				});
			},
			beforeOpen(done, type) {
				if (["edit", "view"].includes(type)) {
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
