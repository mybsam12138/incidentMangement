import React, { useState, useEffect } from "react";
import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/incidents", // 统一的 API 基础地址
  headers: {
    "Content-Type": "application/json",
  },
});

const App = () => {
  const [incidents, setIncidents] = useState([]); // 保存事件列表
  const [error, setError] = useState(null); // 保存异常信息

  // 初始化加载事件列表
  useEffect(() => {
    const fetchIncidents = async () => {
      try {
        const response = await apiClient.get("/");
        setIncidents(response.data);
      } catch (err) {
        setError(err.response?.data || "Failed to fetch incidents.");
      }
    };
    fetchIncidents();
  }, []);

  // 添加事件
  const handleAddIncident = async (newIncident) => {
    try {
      const response = await apiClient.post("/", newIncident);
      setIncidents((prev) => [...prev, response.data]);
    } catch (err) {
      setError(err.response?.data || "Failed to add incident.");
    }
  };

  // 编辑事件
  const handleEditIncident = async (id, updatedIncident) => {
    try {
      const response = await apiClient.put(`/${id}`, updatedIncident);
      setIncidents((prev) =>
        prev.map((incident) =>
          incident.id === id ? response.data : incident
        )
      );
    } catch (err) {
      setError(err.response?.data || "Failed to edit incident.");
    }
  };

  // 删除事件
  const handleDeleteIncident = async (id) => {
    try {
      await apiClient.delete(`/${id}`);
      setIncidents((prev) => prev.filter((incident) => incident.id !== id));
    } catch (err) {
      setError(err.response?.data || "Failed to delete incident.");
    }
  };

  return (
    <div className="container mx-auto p-8">
      <h1 className="text-4xl font-bold mb-6 text-center text-gray-800">
        Incident Management
      </h1>

      {/* 添加事件表单 */}
      <div className="mb-8 bg-white p-6 shadow-md rounded-lg">
        <h2 className="text-2xl font-semibold mb-4 text-gray-700">Add Incident</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            const formData = new FormData(e.target);
            const newIncident = {
              id: formData.get("id"), // 添加 id
              title: formData.get("title"),
              description: formData.get("description"),
            };
            handleAddIncident(newIncident);
            e.target.reset();
          }}
        >
          <div className="grid grid-cols-3 gap-4 mb-4">
            <div>
              <label className="block text-sm font-medium mb-1">ID:</label>
              <input
                type="text"
                name="id"
                className="w-full p-2 border rounded"
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium mb-1">Title:</label>
              <input
                type="text"
                name="title"
                className="w-full p-2 border rounded"
                required
              />
            </div>
            <div>
              <label className="block text-sm font-medium mb-1">Description:</label>
              <input
                type="text"
                name="description"
                className="w-full p-2 border rounded"
                required
              />
            </div>
          </div>
          <button
            type="submit"
            className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
          >
            Add Incident
          </button>
        </form>
      </div>

      {/* 事件列表展示 */}
      <div className="bg-white p-6 shadow-md rounded-lg">
        <h2 className="text-2xl font-semibold mb-4 text-gray-700">Incident List</h2>
        <table className="w-full border-collapse border border-gray-300">
          <thead>
            <tr className="bg-gray-100">
              <th className="border px-4 py-2 text-left">ID</th>
              <th className="border px-4 py-2 text-left">Title</th>
              <th className="border px-4 py-2 text-left">Description</th>
              <th className="border px-4 py-2 text-left">Actions</th>
            </tr>
          </thead>
          <tbody>
            {incidents.map((incident) => (
              <tr key={incident.id} className="hover:bg-gray-50">
                <td className="border px-4 py-2">{incident.id}</td>
                <td className="border px-4 py-2">{incident.title}</td>
                <td className="border px-4 py-2">{incident.description}</td>
                <td className="border px-4 py-2">
                  <button
                    onClick={() => {
                      const updatedTitle = prompt("Edit Title", incident.title);
                      const updatedDescription = prompt(
                        "Edit Description",
                        incident.description
                      );
                      if (updatedTitle && updatedDescription) {
                        handleEditIncident(incident.id, {
                          id: incident.id,
                          title: updatedTitle,
                          description: updatedDescription,
                        });
                      }
                    }}
                    className="text-blue-500 hover:underline mr-4"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => handleDeleteIncident(incident.id)}
                    className="text-red-500 hover:underline"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* 错误弹窗 */}
      {error && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded shadow-md">
            <h2 className="text-lg font-bold mb-4 text-red-500">Error</h2>
            <p className="text-gray-700">{error}</p>
            <button
              onClick={() => setError(null)}
              className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
            >
              Close
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default App;