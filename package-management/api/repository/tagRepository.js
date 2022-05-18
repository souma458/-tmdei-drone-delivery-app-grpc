import { Tag } from "../model/tag.js";

export class TagRepository {
  constructor() {
    this.model = Tag;
  }

  async save(tag) {
    const tagToSave = new Tag(tag);
    return await tagToSave.save();
  }
}
