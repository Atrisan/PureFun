${signature("statement")}
std::shared_future statement${glex.getGlobalVar("asyncnum")} = std::async(std::launch::async, [=]() ${includeArgs("statement/AsyncStatement.ftl", statement.get)}
